package com.example.myapplication.api

import androidx.lifecycle.LiveData
import com.example.myapplication.model.StoriesAndVideos
import com.example.myapplication.util.GenericApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("json-storage/bin/edfefba")
    fun getMedia(): LiveData<GenericApiResponse<StoriesAndVideos>>

}