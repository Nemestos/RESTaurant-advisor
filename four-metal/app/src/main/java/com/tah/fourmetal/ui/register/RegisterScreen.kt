package com.tah.fourmetal.ui.register

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import com.tah.fourmetal.ui.form.Field
import com.tah.fourmetal.ui.form.Form
import com.tah.fourmetal.ui.form.FormState
import com.tah.fourmetal.ui.form.Validator

@Composable
fun RegisterScreen() {
    val context = LocalContext.current

    val state by remember {
        mutableStateOf(FormState())
    }
    Column() {
        Form(
            state = state,
            fields = listOf(
                Field(
                    name = "login",
                    label = "Login:",
                    validators = listOf(Validator.Required())
                ),
                Field(
                    name = "password",
                    label = "Password:",
                    keyboardType = KeyboardType.Password,

                    validators = listOf(Validator.Required())
                ),
                Field(
                    name = "email",
                    label = "Email:",
                    keyboardType = KeyboardType.Email,
                    validators = listOf(Validator.Email())
                ),
                Field(
                    name = "name",
                    label = "Name:",
                    validators = listOf(Validator.Required())
                ),
                Field(
                    name = "firstname",
                    label = "Firstname:",
                    keyboardType = KeyboardType.Number,
                    validators = listOf(Validator.Required())
                )
            )
        )
        Button(onClick = {
            if (state.validate()) {
                Toast.makeText(context, "our work", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Submit")

        }
        Button(onClick = {
            state.clear()
        }) {
            Text("Clear")

        }

    }
}