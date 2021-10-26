package com.example.myapplication.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {

        fun prepareStringForData(inputString: String): String {

            val longValue = inputString.replace(".", "").toLong()

            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            try {
                val date = sdf.format(Date(longValue))
                return date
            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }


}