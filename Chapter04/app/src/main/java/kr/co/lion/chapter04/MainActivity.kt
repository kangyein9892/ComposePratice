package kr.co.lion.chapter04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.co.lion.chapter04.ui.theme.Chapter04Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter04Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    DemoScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    Chapter04Theme {
        DemoScreen()
    }
}

@Composable
fun DemoScreen(){

    var sliderPosition by remember { mutableFloatStateOf(20f) }

    val handlePositionChange = { position : Float ->
        sliderPosition = position
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        DemoText(message = "Welcome to Compose", fontSize = sliderPosition)

        Spacer(modifier = Modifier.height(150.dp))

        DemoSlider(
            sliderPosition = sliderPosition,
            onPositionChange = handlePositionChange
        )

        Text(
            style = MaterialTheme.typography.headlineMedium,
            text = sliderPosition.toInt().toString() + "sp"
        )
    }
}

@Composable
fun DemoSlider(sliderPosition: Float, onPositionChange: (Float) -> Unit){
    Slider(
        modifier = Modifier.padding(10.dp), // 슬라이더 주위 패딩공간
        valueRange = 20f..40f, // 슬라이더 값의 범위
        value = sliderPosition, // 슬라이더 값
        onValueChange = { onPositionChange(it) } // 전달하는 람다 호출
    )
}

@Composable
fun DemoText(message: String, fontSize: Float) {
    Text(text = message,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold)
}

/*@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DemoTextPreview() {
    DemoText(message = "Welcome to Android", fontSize =  12f)
}*/