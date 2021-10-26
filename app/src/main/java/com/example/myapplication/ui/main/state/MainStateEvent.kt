package com.example.myapplication.ui.main.state

sealed class MainStateEvent {

    object GetMedia : MainStateEvent()
    object None : MainStateEvent()

}