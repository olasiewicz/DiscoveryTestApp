package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Stories(
    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("teaser")
    val teaser: String,

    @Expose
    @SerializedName("image")
    val image: String,

    @Expose
    @SerializedName("date")
    var date: String,

    @Expose
    @SerializedName("author")
    val author: String,

    @Expose
    @SerializedName("sport")
    val sport: Sport,

    @Expose
    @SerializedName("views")
    val views: Int,
)
