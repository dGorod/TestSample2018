package ua.dgorod.sample.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 13-Dec-17.
 */
abstract class RxViewModel: ViewModel() {

    protected val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}