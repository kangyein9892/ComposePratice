package kr.co.lion.chapter20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.co.lion.chapter20.ui.theme.Chapter20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter20Theme {
                Surface(color = MaterialTheme.colorScheme.background) {

                }
            }
        }
    }
}

@Composable
fun DemoScreen() {

    var textState by remember { mutableStateOf("") }

    val onTextChange = { text : String ->
        textState = text
    }

    MyTextField(text = textState, onTextChange = onTextChange)
}

// 상태를 부모 함수로 들어올림으로써, MyTextField는 이제 재사용한 비상태 컴포저블
// 부모 함수로부터 접근가능, 다른 컴포저블에도 전달 가능
@Composable
fun MyTextField(text: String, onTextChange : (String) -> Unit) {

    TextField(
        value = text,
        onValueChange = onTextChange
    )

}

@Composable
fun MyTextField1() {

    var textState = remember { mutableStateOf("")}

    val onTextChange = { text : String ->
        textState.value = text
    }

    TextField(
        value = textState.value,
        onValueChange = onTextChange
    )

}

@Composable
fun MyTextField2(){
    var textState by remember { mutableStateOf("") }

    val onTextChange = { text : String ->
        textState = text
    }

    TextField(
        value = textState,
        onValueChange = onTextChange
    )
}

@Composable
fun MyTextField3(){
    var (textValue, setText) = remember { mutableStateOf("")}

    val onTextChange = { text : String ->
        setText(text)
    }

    TextField(
        value = textValue,
        onValueChange = onTextChange
    )
}

@Composable
fun FunctionA(){
    var switchState by remember { mutableStateOf(true) }

    val onSwitchChange = { value: Boolean ->
        switchState = value
    }

    FunctionB(
        switchState = switchState,
        onSwitchChange = onSwitchChange
    )
}

@Composable
fun FunctionB(switchState: Boolean, onSwitchChange: (Boolean) -> Unit){
    Switch(
        checked = switchState,
        onCheckedChange = onSwitchChange
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Chapter20Theme {
        Column{
            DemoScreen()
            FunctionA()
        }
    }
}

