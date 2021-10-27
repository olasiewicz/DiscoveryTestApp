package com.example.myapplication.util

import com.example.myapplication.model.Stories
import com.example.myapplication.model.Videos
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {

        fun ustawDate(data: String): String {

            return if (data.length < 14) {
                data + "0"
            } else {
                data
            }
        }

        fun prepareStringForData(inputDouble: String): String {

            val test = ustawDate(inputDouble)


            val longValue = test.replace(".", "").toLong()

            val sdf = SimpleDateFormat("yyyy-MM-dd, hh:mm", Locale.ENGLISH)
            try {
                val date = sdf.format(Date(longValue))
                return date
            } catch (e: Exception) {
                throw Exception(e)
            }
        }





        fun getListOfStories(inputList: List<Stories>) : MutableList<Stories> {
            var outputListStories = mutableListOf<Stories>()

            inputList.forEach() {
                it.date = DateUtils.prepareStringForData(it.date)
                outputListStories.add(it)
            }

            outputListStories.sortByDescending { it.date }

            return outputListStories
        }

        fun getListOfVideos(inputList: List<Videos>) : MutableList<Videos> {
            var outputListVideos = mutableListOf<Videos>()

            inputList.forEach() {
                it.date = DateUtils.prepareStringForData(it.date)
                outputListVideos.add(it)
            }

            outputListVideos.sortByDescending { it.date }

            return outputListVideos
        }




    }


}