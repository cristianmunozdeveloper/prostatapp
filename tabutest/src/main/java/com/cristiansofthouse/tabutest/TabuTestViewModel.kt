package com.cristiansofthouse.tabutest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cristiansofthouse.tabutest.TabuTestViewModel.Event.Error
import com.cristiansofthouse.tabutest.TabuTestViewModel.Event.Success
import com.cristiansofthouse.testhistory.controller.TestHistoryController
import com.cristiansofthouse.testhistory.models.TestHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TabuTestViewModel @Inject constructor(
    private val testHistoryController: TestHistoryController
) : ViewModel() {

    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event> get() = _event

    private val disposable = CompositeDisposable()

    fun saveTestHistory(score: String) {
        disposable.add(
            testHistoryController.insertTestHistory(
                TestHistory(0, "Tabutest", score, Date().time)
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _event.value = Success },
                    { _event.value = Error }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    sealed class Event {
        object Success : Event()
        object Error : Event()
    }
}
