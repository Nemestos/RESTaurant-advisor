package com.tah.fourmetal.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import com.tah.fourmetal.ui.theme.Reenie
import com.tah.fourmetal.ui.viewmodels.AuthViewModel
import com.tah.fourmetal.ui.viewmodels.CheckRightsViewModel
import okhttp3.internal.immutableListOf
import org.koin.androidx.compose.getViewModel

data class Localization(
    val streetNumber: String?,
    val streetName: String?,
    val postalCode: String?,
    val city: String?
)

class Utils {

    companion object {
        val localizationRegex =
            "([0-9]+)?,? ?([^0-9]+)?([0-9\\s]+)?\\s?([^0-9]+)?"

        fun toLocalization(raw: String): Localization {

            val result = localizationRegex.toRegex().matchEntire(raw)!!.groups
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
            .fillMaxWidth()
            .size(64.dp),
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center
    )
}

@Composable
fun AbilityButton(
    modifier: Modifier = Modifier,
    textString: String,
    fontSize: Int,
    onClick: () -> Unit,
    abilities: List<String>
) {
    val cvm = getViewModel<CheckRightsViewModel>()
    val avm = getViewModel<AuthViewModel>()
    val currUser = avm.sessionManager.currentUserFlow.collectAsState(initial = null)
    val currState = cvm.currState
    LaunchedEffect(key1 = Unit) {
        cvm.checkRights(currUser.value, abilities)
    }
    if (currState) {
        Button(
            modifier = modifier,

            onClick = onClick,

            ) {
            Text(
                text = textString,
                fontFamily = Reenie,
                textAlign = TextAlign.Center,
                fontSize = fontSize.sp
            )
        }
    }

}