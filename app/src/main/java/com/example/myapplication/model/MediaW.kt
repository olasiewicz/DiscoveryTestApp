package com.example.myapplication.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MediaW(

    @Expose
    @SerializedName("videos")
    val listOfVideos: MutableList<Videos>,

    @Expose
    @SerializedName("stories")
    val listOfStories: MutableList<Stories>
)