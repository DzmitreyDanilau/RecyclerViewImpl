package com.example.recyclerviewimpl.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.recyclerviewimpl.data.models.Item
import com.example.recyclerviewimpl.domain.Interactor
import com.example.recyclerviewimpl.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RecyclerViewModel @Inject constructor(private val interactor: Interactor) : BaseViewModel() {


    private val state by lazy {
        MutableLiveData<State>()
    }

    fun getState() = state as LiveData<State>


    fun fetchData() {
        interactor.fetchData()
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                Timber.d("Response: $it") }
            .observeOn(AndroidSchedulers.mainThread())
            .toIrw()
            .subscribe {
                when (it) {
                    is InteractorResponseWrapper.Data -> {
                        State.Data(it.data)
                    }
                    is InteractorResponseWrapper.Error -> {
                        State.Error(it.error)
                    }
                    is InteractorResponseWrapper.Progress -> {
                        State.Progress
                    }
                }
            }.unSubscriveOnDestroy()
    }


    sealed class State {
        data class Data(val list: List<Item>) : State()
        data class Error(val error: Throwable) : State()
        object Progress : State()
    }

}