package com.tah.fourmetal.ui.form

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Form(
    state: FormState,
    fields: List<Field>,
    onSubmitClick: () -> Unit,
    onClearClick: () -> Unit,
) {
    state.fields = fields
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        fields.forEach {
            it.content()
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = { onSubmitClick() }) {
            Text("Submit")

        }
        Button(onClick = { onClearClick() }) {
            Text("Clear")

        }
    }
}