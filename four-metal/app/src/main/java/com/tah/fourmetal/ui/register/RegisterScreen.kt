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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tah.fourmetal.ui.form.Field
import com.tah.fourmetal.ui.form.Form
import com.tah.fourmetal.ui.form.FormState
import com.tah.fourmetal.ui.form.Validator
import com.tah.fourmetal.ui.viewmodels.AuthViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun RegisterScreen() {

    val avm = getViewModel<AuthViewModel>()
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
                    validators = listOf(Validator.Required())
                ),
                Field(
                    name = "age",
                    label = "Age:",
                    keyboardType = KeyboardType.Number,
                    validators = listOf(Validator.Required())
                )
            )
        )
        if (!avm.errorMsg.isEmpty()) {
            Text(text = avm.errorMsg, color = Color.Red)
        }
        if (!avm.successMsg.isEmpty()) {
            Text(text = avm.successMsg, color = Color.Green)
        }
        Button(onClick = {
            if (state.validate()) {
                val infos = state.getData();
                avm.register(
                    infos["login"].orEmpty(),
                    infos["password"].orEmpty(),
                    infos["email"].orEmpty(),
                    infos["name"].orEmpty(),
                    infos["firstname"].orEmpty(),
                    infos["age"].orEmpty().toInt()
                )
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