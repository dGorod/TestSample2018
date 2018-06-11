package ua.dgorod.sample.data.repository

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import ua.dgorod.sample.data.api.ApiInterface
import ua.dgorod.sample.data.db.MyDatabase
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
        val networkCall = api.getRepositories("android created:>2016-01-01 stars:>100", page = page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { dto ->
                            db.runInTransaction {
                                dto.items.forEach { repo ->
                                    // User first because of foreign key constraint.
                                    db.users().insert(userDtoMapper.map(repo.owner))
                                    db.repositories().insert(repoDtoMapper.map(repo))
                                }
                            }
                        },
                        { Timber.e(it) }
                )

        return db.repositories().getAllWithUsers()
                .map { entityMapper.map(it) }
                .doOnTerminate { networkCall.dispose() }
    }

    override fun get(id: Long): Maybe<Repo> = db.repositories().getWithUser(id).map { entityMapper.map(it) }
}