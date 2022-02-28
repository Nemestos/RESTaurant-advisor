package com.tah.fourmetal.ui

import android.util.Log
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import okhttp3.internal.immutableListOf

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
            Log.d("regex:", localization.toString())
            return localization
        }
    }
}

@Composable
fun RandomIcon(modifier: Modifier = Modifier, elts: List<Int> = immutableListOf()) {
    val random = elts.random()
    Icon(
        painterResource(
            id = random
        ),
        "random logo",
        modifier = modifier
    )

}