package com.tah.fourmetal.ui.restaurant.details

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.tah.fourmetal.ui.theme.Reenie

@Composable
fun OpenMaps(modifier: Modifier = Modifier, address: String, text: String, fontSize: Int) {
    val context = LocalContext.current
    Button(
        modifier = modifier,
        onClick = {
            val mapsUri = Uri.parse("geo:0,0?q=1600 $address")
            val mapIntent = Intent(Intent.ACTION_VIEW, mapsUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            try {

                context.startActivity(mapIntent)
            }catch (e:Exception){
                Toast.makeText(context,"cant open maps application",Toast.LENGTH_SHORT).show()
            }
        }) {
        Text(
            text = text, fontFamily = Reenie,
            textAlign = TextAlign.Center,
            fontSize = fontSize.sp
        )

    }
}