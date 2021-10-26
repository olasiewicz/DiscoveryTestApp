package com.example.myapplication.api

import androidx.lifecycle.LiveData
import com.codingwithmitch.mviexample.util.GenericApiResponse
import com.example.myapplication.model.MediaW
import retrofit2.http.GET

interface ApiService {

    @GET("json-storage/bin/edfefba")
    fun getMedia(): LiveData<GenericApiResponse<MediaW>>

}