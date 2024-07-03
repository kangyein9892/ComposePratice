package kr.co.lion.chapter19

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import kr.co.lion.chapter19.ui.theme.Chapter19Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter19Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun CustomText(text: String, fontWeight: FontWeight, color: Color) {
    Text(text = text, fontWeight = fontWeight, color = color)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    /*CustomText(
        text = "Hello Compose",
        fontWeight = FontWeight.Bold,
        color = Color.Magenta
    )*/
    Chapter19Theme {
        CustomList(listOf("One", "Two", "Three", "Four", "Five", "Six"))
    }
}

@Composable
@Preview(showBackground = true)
fun CustomSwitch() {
    val checked = remember { mutableStateOf(true) }

    Column {
        Switch(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )
        if(checked.value) {
            Text("Switch is On")
        } else {
            Text("Switch is Off")
        }
    }
}

@Composable
fun CustomList(items: List<String>) {
    Column {
        for (item in items) {
            Text(item)
            Divider(color = Color.Black)
        }
    }
}