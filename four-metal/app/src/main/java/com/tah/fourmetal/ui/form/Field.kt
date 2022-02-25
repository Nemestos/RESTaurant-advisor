package com.tah.fourmetal.ui.form

import android.util.Patterns
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

class Field(
    val name: String,
    val label: String = "",
    val validators: List<Validator>,
    val keyboardType: KeyboardType = KeyboardType.Text
) {
    var text: String by mutableStateOf("")
    var lbl: String by mutableStateOf(label)
    var hasAnyError: Boolean by mutableStateOf(false)

    fun clear() {
        text = ""
        clearErr()
    }

    private fun showErr(error: String) {
        hasAnyError = true
        lbl = error

    }

    private fun clearErr() {
        hasAnyError = false
        lbl = label
    }

    @Composable
    fun content() {
        TextField(
            value = text,
            isError = hasAnyError,
            label = { Text(text = lbl) },
            modifier = Modifier.padding(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
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
                else -> false
            }
        }.all { it }
    }

}