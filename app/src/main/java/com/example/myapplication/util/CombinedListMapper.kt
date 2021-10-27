package com.example.myapplication.util

import com.example.myapplication.model.StoriesAndVideos
import com.example.myapplication.util.DateUtils.prepareSortedByDateListOfStories
import com.example.myapplication.util.DateUtils.prepareSortedByDateListOfVideos

object CombinedListMapper {

    fun mapResponseObjectToSingleSortedList(response: StoriesAndVideos): List<Any> {

        val listOfStories = prepareSortedByDateListOfStories(response.listOfStories)
        val listOfVideos = prepareSortedByDateListOfVideos(response.listOfVideos)


        var restSize = 0
        val size = if (listOfVideos.size < listOfStories.size) {
            restSize = listOfStories.size - listOfVideos.size
            listOfVideos.size
        } else {
            restSize = listOfVideos.size - listOfStories.size
            listOfStories.size
        }

        val combinedList = mutableListOf<Any>()

        for (i in 0 until size) {
            combinedList.add(listOfStories[i])
            combinedList.add(listOfVideos[i])
        }

        for (i in 0 until restSize) {
            if (listOfVideos.size > listOfStories.size) {
                combinedList.add(listOfVideos[i])
            } else {
                combinedList.add(listOfStories[i])
            }
        }

        return combinedList
    }

}