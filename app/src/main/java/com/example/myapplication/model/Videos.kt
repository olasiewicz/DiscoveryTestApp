package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Videos(

    @Expose
    @SerializedName("title")
    val title: String
)
