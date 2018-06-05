package ua.dgorod.sample.data.api

import androidx.paging.PositionalDataSource
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import ua.dgorod.sample.data.api.dto.RepoDto

/**
 * Created by dgorodnytskyi on 6/5/18.
 */
class RepoApiDataSource(
        private val api: ApiInterface,
        private val query: String
) : PositionalDataSource<RepoDto>() {

    private var networkCall: Disposable? = null

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<RepoDto>) {
        networkCall?.dispose()

        networkCall = api.getRepositories(query, page = params.requestedStartPosition, pageSize = params.pageSize)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { callback.onResult(it.items, params.requestedStartPosition, it.total) },
                        { Timber.e(it) }
                )
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<RepoDto>) {
        networkCall?.dispose()

        networkCall = api.getRepositories(query, page = params.startPosition, pageSize = params.loadSize)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { callback.onResult(it.items) },
                        { Timber.e(it) }
                )
    }

    fun cancel() {
        networkCall?.dispose()
    }
}