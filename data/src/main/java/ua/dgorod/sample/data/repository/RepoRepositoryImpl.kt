package ua.dgorod.sample.data.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import ua.dgorod.sample.data.api.ApiInterface
import ua.dgorod.sample.data.api.dto.RepoDto
import ua.dgorod.sample.data.db.MyDatabase
import ua.dgorod.sample.data.db.entity.RepoInfoEntity
import ua.dgorod.sample.data.mapper.RepoDtoMapper
import ua.dgorod.sample.data.mapper.RepoEntityMapper
import ua.dgorod.sample.data.mapper.UserDtoMapper
import ua.dgorod.sample.domain.Const
import ua.dgorod.sample.domain.model.Repo
import ua.dgorod.sample.domain.repository.RepoRepository

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
class RepoRepositoryImpl(
        private val api: ApiInterface,
        private val db: MyDatabase
): RepoRepository {

    private val repoDtoMapper = RepoDtoMapper()
    private val userDtoMapper = UserDtoMapper()
    private val entityMapper = RepoEntityMapper()

    override fun getAll(page: Int): Flowable<List<Repo>> {
        val pageSize = Const.DEFAULT_PAGE_SIZE
        val boundary = RepoBoundaryCallback(pageSize)
        val localSource = db.repositories().getAllWithUsers()

        return RxPagedListBuilder(localSource, pageSize)
                .setInitialLoadKey(page)
                .setBoundaryCallback(boundary)
                .buildFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .doOnTerminate { boundary.cancel() }
                .map { entityMapper.map(it) }
    }

    override fun get(id: Long): Maybe<Repo> = db.repositories().getWithUser(id).map { entityMapper.map(it) }

    inner class RepoBoundaryCallback(private val pageSize: Int) : PagedList.BoundaryCallback<RepoInfoEntity>() {

        private var networkCall: Disposable? = null

        override fun onZeroItemsLoaded() { makeNetworkCall(1) }

        override fun onItemAtEndLoaded(itemAtEnd: RepoInfoEntity) {
            val position = db.repositories().getAllIds().indexOf(itemAtEnd.repo.id)
            makeNetworkCall(position / pageSize)
        }

        fun cancel() { networkCall?.dispose() }

        private fun makeNetworkCall(page: Int) {
            networkCall?.dispose()

            networkCall = api.getRepositories("android created:>2016-01-01 stars:>100", page = page)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                            { dto ->
                                db.runInTransaction {
                                    dto.items.forEach { repo ->
                                        db.repositories().insert(repoDtoMapper.map(repo))
                                        db.users().insert(userDtoMapper.map(repo.owner))
                                    }
                                }
                            },
                            { Timber.e(it) }
                    )
        }
    }
}