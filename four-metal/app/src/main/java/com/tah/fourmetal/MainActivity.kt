package com.tah.fourmetal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.tah.fourmetal.ui.navigation.BottomNavigation
import com.tah.fourmetal.ui.navigation.NavigationGraph
import com.tah.fourmetal.ui.restaurant.RestaurantListScreen
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel

const val EXTRA_MESSAGE = "com.tah.fourmetal.MESSAGE"

class TodoViewModel() : ViewModel() {
    var todoList = mutableStateListOf<String>()
    var currValue by mutableStateOf("")


}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MainScreenContent()

        }
    }
}

@Composable
fun MainScreenContent() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) },
    ) {
        NavigationGraph(navController = navController)
    }
}

@Composable
fun TodoScreenContent(todoViewModel: TodoViewModel = TodoViewModel()) {
    Column {
        TodoAdder(
            currValue = todoViewModel.currValue,
            onAdd = { todoViewModel.todoList.add(todoViewModel.currValue) },
            onValueChange = { todoViewModel.currValue = it }
        )
        if (todoViewModel.todoList.size > 0) {
            Spacer(modifier = Modifier.height(1.dp))
            ClearAll(list = todoViewModel.todoList)
        }
        Spacer(modifier = Modifier.height(1.dp))
        TodoList(
            todoList = todoViewModel.todoList,
            onRemove = { todoViewModel.todoList.remove(it) })

    }

}

@Composable
fun ClearAll(list: SnapshotStateList<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Button(
            modifier = Modifier
                .padding(all = 5.dp)
                .fillMaxWidth()
                .weight(100f)
                .height(60.dp),
            onClick = { list.clear() }
        ) {
            Text(
                text = "Clear all",
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun TodoAdder(currValue: String, onAdd: () -> Unit, onValueChange: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        TextField(
            value = currValue,
            onValueChange =
            {
                onValueChange(it)
            },
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant),
            placeholder = { Text(text = "Entrer votre valeur") },
            keyboardOptions = KeyboardOptions(
                autoCorrect = true
            ),

            textStyle = TextStyle(
                color = Color.Black, fontSize = TextUnit.Unspecified,
                fontFamily = FontFamily.Monospace
            ),
            maxLines = 1,
            singleLine = true,

            )
        Button(
            onClick = { onAdd() },
            modifier = Modifier
                .weight(0.2f)
                .fillMaxHeight()
        ) {
            Text(text = "ADD")

        }
    }
}

@Composable
fun TodoList(todoList: SnapshotStateList<String>, onRemove: (String) -> Unit) {
    Surface(modifier = Modifier.padding(all = 5.dp)) {
        LazyColumn {
            itemsIndexed(todoList) { i, item ->
                TodoItem(name = item, onClick = { onRemove(item) })
            }
        }

    }
}

@Composable
fun TodoItem(name: String, onClick: () -> Unit) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Delete")
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text = name, Modifier.weight(0.85f))
        ClickableText(
            text = annotatedText,
            onClick = { onClick() },
            modifier = Modifier.weight(0.15f)
        )

    }
}

@Composable
fun NamesList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(items = names) {
            ClickableGreeting(name = it, Modifier.padding(5.dp))
            Divider()
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { updateCount(count + 1) }) {
        Text(text = "Je suis clické $count")
    }
}

@Composable
fun ClickableGreeting(name: String, modifier: Modifier = Modifier) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    val targetColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colors.primary else Color.Transparent,
        animationSpec = tween(500)
    );
    Surface(color = targetColor) {
        Greeting(name = name, Modifier.clickable { isSelected = !isSelected })

    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        color = MaterialTheme.colors.secondary,
        fontSize = 30.sp,
        text = "Hello $name",
    )
}