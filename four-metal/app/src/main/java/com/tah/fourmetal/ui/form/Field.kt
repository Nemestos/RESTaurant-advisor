package com.tah.fourmetal.ui.form

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

open class Field(
    val name: String,
    val label: String = "",
    val defaultValue: String = "",
    val validators: List<Validator>,
    val keyboardType: KeyboardType = KeyboardType.Text,
    val autoComplete: Boolean = true
) {
    var text: String by mutableStateOf(defaultValue)
    var lbl: String by mutableStateOf(label)
    var hasAnyError: Boolean by mutableStateOf(false)

    fun clear() {
        text = ""
        clearErr()
    }

    protected fun showErr(error: String) {
        hasAnyError = true
        lbl = error

    }

    protected fun clearErr() {
        hasAnyError = false
        lbl = label
    }

    @Composable
    open fun content() {
        TextField(
            value = text,
            isError = hasAnyError,
            label = { Text(text = lbl) },
            modifier = Modifier
                .padding()
                .clip(RoundedCornerShape(20))
                .background(Color.White),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                autoCorrect = autoComplete
            ),
            onValueChange = {
                clearErr()
                text = it
            }
        )
    }

    fun validate(): Boolean {
        return validators.map {
            when (it) {
                is Validator.Email -> {
                    if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                        showErr(it.message)
                        return@map false
                    }
                    true
                }
                is Validator.Required -> {
                    if (text.isEmpty()) {
                        showErr(it.message)
                        return@map false
                    }
                    true
                }
                is Validator.Regex -> {
                    if (!it.regex.toRegex().containsMatchIn(text)) {
                        showErr(it.message)
                        return@map false
                    }
                    true

                }
            }
        }.all { it }
    }

}

class PasswordField(
    name: String,
    label: String = "Password",
    defaultValue: String = "",
    validators: List<Validator>,
    keyboardType: KeyboardType = KeyboardType.Password,
    autoComplete: Boolean = false
) : Field(name, label, defaultValue, validators, keyboardType, autoComplete) {


    @Composable
    override fun content() {
        var passwordVisibility by remember { mutableStateOf(false) }
        TextField(
            value = text,
            isError = hasAnyError,
            label = { Text(text = lbl) },
            modifier = Modifier
                .padding()
                .clip(RoundedCornerShape(20))
                .background(Color.White),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                autoCorrect = autoComplete
            ),
            trailingIcon = {
                val image =
                    if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(imageVector = image, contentDescription = "")

                }
            },
            onValueChange = {
                clearErr()
                text = it
            }
        )

    }

}