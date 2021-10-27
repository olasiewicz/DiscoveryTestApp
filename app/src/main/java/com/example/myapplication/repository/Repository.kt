package com.example.myapplication.repository

import androidx.lifecycle.LiveData

import com.example.myapplication.api.RetrofitBuilder
import com.example.myapplication.model.MediaW
import com.example.myapplication.ui.main.state.MainViewState
import com.example.myapplication.util.ApiSuccessResponse
import com.example.myapplication.util.DataState
import com.example.myapplication.util.DateUtils
import com.example.myapplication.util.DateUtils.Companion.prepareStringForData
import com.example.myapplication.util.GenericApiResponse

object Repository {

    fun getMedia(): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<MediaW, MainViewState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<MediaW>) {

                val a = response.body


                val outListaS = DateUtils.getListOfStories(a.listOfStories)
                val outListV = DateUtils.getListOfVideos(a.listOfVideos)


                var restSize = 0
                val size = if (outListV.size < outListaS.size) {
                    restSize = outListaS.size - outListV.size
                    outListV.size
                } else {
                    restSize = outListV.size - outListaS.size
                    outListaS.size
                }

//                restSize = outListV.size + outListaS.size - size


                val listaCala = mutableListOf<Any>()

                for (i in 0 until size) {
                    listaCala.add(outListaS[i])
                    listaCala.add(outListV[i])
                }

                for (i in 0 until restSize) {
                    if (outListV.size > outListaS.size) {
                        listaCala.add(outListV[i])
                    } else {
                        listaCala.add(outListaS[i])
                    }
                }





                result.value = DataState.data(
                    null,
                    MainViewState(

                        media = listaCala
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<MediaW>> {
                return RetrofitBuilder.apiService.getMedia()
            }

        }.asLiveData()
    }
}
