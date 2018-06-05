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

    val loadingStatus: MutableLiveData<Boolean> = MutableLiveData()
    val reposList: MutableLiveData<Parcel<List<RepoUiModel>>> = MutableLiveData()

    fun getRepositories(page: Int, firstLoad: Boolean = false) {
        if (firstLoad && reposList.value?.content?.isNotEmpty() == true) {
            // no need to load data again
            return
        }

        disposable.add(repoSource.getAll(page)
                .filter { it.isNotEmpty() }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingStatus.value = true }
                .doOnNext { loadingStatus.value = false }
                .doOnError { Timber.e(it) }
                .subscribe(
                        { reposList.value = Parcel.success(repoMapper.map(it)) },
                        { reposList.value = Parcel.error(it) }
                )
        )
    }
}