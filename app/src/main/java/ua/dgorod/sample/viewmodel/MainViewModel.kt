package ua.dgorod.sample.viewmodel

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import ua.dgorod.sample.domain.interactor.RepoInteractor
import ua.dgorod.sample.mapper.RepoUiMapper
import ua.dgorod.sample.model.RepoUiModel

/**
 * Created by dgorodnytskyi on 6/5/18.
 */
open class MainViewModel(private val repoSource: RepoInteractor): RxViewModel() {

    private val repoMapper = RepoUiMapper()

    val emptyState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingStatus: MutableLiveData<Boolean> = MutableLiveData()
    val reposList: MutableLiveData<Parcel<List<RepoUiModel>>> = MutableLiveData()

    fun getRepositories(page: Int) {
        disposable.add(repoSource.getAll(page)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingStatus.value = true }
                .doOnNext {
                    loadingStatus.value = false
                    emptyState.value = it.isEmpty()
                }
                .doOnError { Timber.e(it) }
                .subscribe(
                        { reposList.value = Parcel.success(repoMapper.map(it)) },
                        { reposList.value = Parcel.error(it) }
                )
        )
    }
}