package com.tah.fourmetal.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import com.tah.fourmetal.ui.theme.Reenie
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

@Composable
fun RandomImage(modifier: Modifier = Modifier, elts: List<Int> = immutableListOf()) {
    val random = elts.random()
    val context = LocalContext.current
    Image(
        rememberImagePainter(ContextCompat.getDrawable(context, random)),
        "random image",
        modifier = modifier
    )

}

@Composable
fun HeaderTitle(text: String) {
    Text(
        text = text,
        fontFamily = Reenie,
        style = MaterialTheme.typography.h3,
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center
    )
}