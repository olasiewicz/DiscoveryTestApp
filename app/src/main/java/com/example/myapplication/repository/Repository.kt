package com.example.myapplication.repository

import androidx.lifecycle.LiveData

import com.codingwithmitch.mviexample.util.ApiSuccessResponse
import com.codingwithmitch.mviexample.util.DataState
import com.codingwithmitch.mviexample.util.GenericApiResponse
import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.model.MediaW
import com.example.myapplication.ui.main.state.MainViewState

object Repository {

    fun getMedia(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<MediaW, MainViewState>(){

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<MediaW>) {
                result.value = DataState.data(
                    null,
                    MainViewState(
                        media = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<MediaW>> {
                return RetrofitBuilder.apiService.getMedia()
            }

        }.asLiveData()
    }
}
