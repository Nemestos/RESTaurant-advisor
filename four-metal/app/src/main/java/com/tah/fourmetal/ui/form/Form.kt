package com.tah.fourmetal.ui.form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Form(state: FormState, fields: List<Field>) {
    state.fields = fields
    Column() {
        fields.forEach {
            it.content()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}