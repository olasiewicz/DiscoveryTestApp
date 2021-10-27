package com.example.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.Repository
import com.example.myapplication.ui.main.state.MainStateEvent
import com.example.myapplication.ui.main.state.MainViewState
import com.example.myapplication.util.AbsentLiveData
import com.example.myapplication.util.DataState

class MainViewModel : ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState


    val dataState: LiveData<DataState<MainViewState>> = Transformations
        .switchMap(_stateEvent) { stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
        println("DEBUG: New StateEvent detected: $stateEvent")
        when (stateEvent) {

            is MainStateEvent.GetMedia -> {
                return Repository.getMedia()
            }

            is MainStateEvent.None -> {
                return AbsentLiveData.create()
            }
        }
    }

    fun setStateEvent(event: MainStateEvent) {
        val state: MainStateEvent
        state = event
        _stateEvent.value = state
    }

    fun setMedia(media: List<Any>) {


        val update = getCurrentViewStateOrNew()


//
//        val size = if (outListV.size < outListaS.size)  outListV.size else outListaS.size
//
//        val restSize = outListV.size + outListaS.size - size
//
//
//        val listaCala = mutableListOf<Any>()
//
//        for (i in 0 until size) {
//            listaCala.add{ outListaS[i] }
//            listaCala.add( outListV[i] )
//        }
//
//        for (i in 0 until restSize) {
//            if (outListV.size > outListaS.size) {
//                listaCala.add(outListV[i])
//            } else {
//                listaCala.add(outListaS[i])
//            }
//        }

        update.media = media

            _viewState.value = update
}

fun getCurrentViewStateOrNew(): MainViewState {
    val value = viewState.value?.let {
        it
    } ?: MainViewState()
    return value
}

}