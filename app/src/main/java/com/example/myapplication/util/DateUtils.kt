package com.example.myapplication.util

import com.example.myapplication.model.Stories
import com.example.myapplication.model.Videos
import com.example.myapplication.util.Constants.TIME_STRING_LENGHT
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private fun checkDateLenght(date: String): String {

        return if (date.length < TIME_STRING_LENGHT) {
            date + "0"
        } else {
            date
        }
    }

    private fun prepareStringForData(inputDouble: String): String {
        val test = checkDateLenght(inputDouble)
        val longValue = test.replace(".", "").toLong()
        val sdf = SimpleDateFormat("yyyy.MM.dd, hh:mm", Locale.ENGLISH)
        try {
            val date = sdf.format(Date(longValue))
            return date
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    fun prepareSortedByDateListOfStories(inputList: List<Stories>): MutableList<Stories> {
        val outputListStories = mutableListOf<Stories>()

        inputList.forEach() {
            it.date = prepareStringForData(it.date)
            outputListStories.add(it)
        }
        outputListStories.sortByDescending { it.date }

        return outputListStories
    }

    fun prepareSortedByDateListOfVideos(inputList: List<Videos>): MutableList<Videos> {
        val outputListVideos = mutableListOf<Videos>()

        inputList.forEach() {
            it.date = prepareStringForData(it.date)
            outputListVideos.add(it)
        }

        outputListVideos.sortByDescending { it.date }

        return outputListVideos
    }

}