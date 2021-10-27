package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.model.StoriesAndVideos
import com.example.myapplication.ui.main.state.MainViewState
import com.example.myapplication.util.ApiSuccessResponse
import com.example.myapplication.util.CombinedListMapper
import com.example.myapplication.util.DataState
import com.example.myapplication.util.GenericApiResponse

object Repository {

    fun getMedia(): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<StoriesAndVideos, MainViewState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<StoriesAndVideos>) {

                result.value = DataState.data(
                    null,
                    MainViewState(
                        media = CombinedListMapper.mapResponseObjectToSingleSortedList(response.body)
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<StoriesAndVideos>> {
                return RetrofitBuilder.apiService.getMedia()
            }

        }.asLiveData()
    }
}
