package com.tah.fourmetal.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tah.fourmetal.data.SessionManager
import com.tah.fourmetal.ui.form.Field
import com.tah.fourmetal.ui.form.Form
import com.tah.fourmetal.ui.form.FormState
import com.tah.fourmetal.ui.form.Validator
import com.tah.fourmetal.ui.viewmodels.AuthViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScreen() {
    val avm = getViewModel<AuthViewModel>()
    val state by remember {
        mutableStateOf(FormState())
    }
    val authUser by avm.sessionManager.currentUserFlow.collectAsState(initial = null)

    Column() {
        Form(
            state = state,
            fields = listOf(
                Field(
                    name = "login",
                    label = "Login:",
                    autoComplete = false,
                    validators = listOf(Validator.Required())
                ),
                Field(
                    name = "password",
                    label = "Password:",
                    keyboardType = KeyboardType.Password,
                    validators = listOf(Validator.Required())
                )
            )
        )
        if (!avm.errorMsg.isEmpty()) {
            Text(text = avm.errorMsg, color = Color.Red)
        }
        if (authUser != null) {
            Text(text = authUser!!.token.orEmpty(), color = Color.Green)

        }
        Button(onClick = {
            if (state.validate()) {
                val infos = state.getData();
                avm.clear()
                avm.login(
                    infos["login"].orEmpty(),
                    infos["password"].orEmpty()
                )

            }
        }) {
            Text("Submit")

        }

        Button(onClick = {
            state.clear()
            avm.clear()
        }) {
            Text("Clear")

        }

    }
}