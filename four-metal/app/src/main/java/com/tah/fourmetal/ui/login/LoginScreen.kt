package com.tah.fourmetal.ui.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tah.fourmetal.data.SessionManager
import com.tah.fourmetal.ui.form.*
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

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Form(
            state = state,
            fields = listOf(
                Field(
                    name = "login",
                    label = "Login:",
                    autoComplete = false,
                    validators = listOf(Validator.Required())
                ),
                PasswordField(
                    name = "password",
                    validators = listOf(Validator.Required())
                )
            ),
            onSubmitClick = {
                if (state.validate()) {
                    val infos = state.getData();
                    avm.clear()
                    avm.login(
                        infos["login"].orEmpty(),
                        infos["password"].orEmpty()
                    )

                }
            },
            onClearClick = {
                state.clear()
                avm.clear()
            }
        )
        if (!avm.errorMsg.isEmpty()) {
            Text(text = avm.errorMsg, color = Color.Red)
        }
        if (authUser != null) {
            Text(text = authUser!!.token.orEmpty(), color = Color.Green)

        }


    }
}