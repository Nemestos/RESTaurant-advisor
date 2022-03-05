package com.tah.fourmetal.ui.form

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

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
    label: String = "",
    defaultValue: String = "",
    validators: List<Validator>,
    keyboardType: KeyboardType = KeyboardType.Text,
    autoComplete: Boolean = true
) : Field(name, label, defaultValue, validators, keyboardType, autoComplete) {
    @Composable
    override fun content() {
        TextField(
            value = text,
            isError = hasAnyError,
            label = { Text(text = lbl) },
            modifier = Modifier
                .padding()
                .clip(RoundedCornerShape(20))
                .background(Color.White),
            visualTransformation = 
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

}