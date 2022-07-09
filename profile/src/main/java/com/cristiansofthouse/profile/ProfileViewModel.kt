package com.cristiansofthouse.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cristiansofthouse.profile.model.ProfileActions
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ProfileViewModel : ViewModel() {

    private val controller: ProfileController = ProfileControllerImpl()
    private val disposable = CompositeDisposable()

    private val _action = MutableLiveData<ProfileActions>()
    val action: LiveData<ProfileActions> get() = _action

    fun operationImc(weight: String, size: String) {
        disposable.add(
            controller.resultIMC(weight, size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _action.value = ProfileActions.ShowResult(it)
                }, {
                    _action.value = ProfileActions.ShowError(it.message)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
