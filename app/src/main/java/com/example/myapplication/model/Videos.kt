package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Videos(

    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("thumb")
    val thumb: String,

    @Expose
    @SerializedName("url")
    val url: String,

    @Expose
    @SerializedName("date")
    var date: String,

    @Expose
    @SerializedName("sport")
    val sport: Sport,

    @Expose
    @SerializedName("views")
    val views: Int,
)
