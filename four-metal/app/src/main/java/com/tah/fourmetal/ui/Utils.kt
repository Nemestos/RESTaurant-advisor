package com.tah.fourmetal.ui

import android.util.Log

data class Localization(
    val streetNumber: String?,
    val streetName: String?,
    val postalCode: String?,
    val city: String?
)

class Utils {
    companion object {
        fun toLocalization(raw: String): Localization {
            val regex =
                "([0-9]+)?,? ?([^0-9]+)?([0-9\\s]+)?\\s?([^0-9]+)?".toRegex()
            val result = regex.matchEntire(raw)!!.groups
            val localization = Localization(
                result[1]?.value,
                result[2]?.value,
                result[3]?.value,
                result[4]?.value,
            )
            Log.d("regex:",localization.toString())
            return localization
        }
    }
}