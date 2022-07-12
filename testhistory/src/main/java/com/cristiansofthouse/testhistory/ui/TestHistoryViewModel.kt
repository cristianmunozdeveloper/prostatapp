package com.cristiansofthouse.testhistory.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cristiansofthouse.testhistory.controller.TestHistoryController
import com.cristiansofthouse.testhistory.models.TestHistory
import com.cristiansofthouse.testhistory.ui.TestHistoryViewModel.Event.ShowTestHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class TestHistoryViewModel @Inject constructor(
    private val controller: TestHistoryController
) : ViewModel() {

    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event> get() = _event

    private val disposable = CompositeDisposable()

    init {
        getTestHistory()
    }

    private fun getTestHistory() {
        disposable.add(
            controller.getTestHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _event.value = ShowTestHistory(it)
                    },
                    {
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    sealed class Event {
        data class ShowTestHistory(val list: List<TestHistory>) : Event()
    }
}
