package kr.co.lion.chapter27

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.lion.chapter27.ui.theme.Chapter27Theme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter27Theme {
                // A surface container using the 'background' color from the theme
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
fun ColorBox(modifier: Modifier) {
    Box(
        Modifier
            .padding(1.dp)
            .size(width = 50.dp, height = 10.dp)
            .then(modifier))
}

@Composable
fun MainScreen() {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.size(120.dp, 80.dp)) {
        ColorBox(
            Modifier
                .exampleLayout(90, 50)
                .background(Color.Blue)
        )
        /*Column {
            ColorBox(
                Modifier.exampleLayout(0f).background(Color.Blue)
            )
            ColorBox(
                Modifier.exampleLayout(0.25f).background(Color.Green)
            )
            ColorBox(
                Modifier.exampleLayout(0.5f).background(Color.Yellow)
            )
            ColorBox(
                Modifier.exampleLayout(0.25f).background(Color.Red)
            )
            ColorBox(
                Modifier.exampleLayout(0.0f).background(Color.Magenta)
            )
        }*/
    }
}

fun Modifier.exampleLayout(
    x: Int,
    y: Int
) = layout { measurable, constraints ->

    val placeable = measurable.measure(constraints)

    // layout 메서드 호출, placeable 값으로부터 높이와 폭 전달
    // 자식의 위치를 지정하는 layout() 메서드에 후행 람다 전달
    layout(placeable.width, placeable.height){
        // 후행 람다 안에서 placeable 객체의 placeRelative() 메서드 호출을 통해 각자 자식 요소 위치 지정 -> 모디파이어에 전달된 새로운 x,y 좌표 이용
        placeable.placeRelative(x, y)
    }
}

fun Modifier.exampleLayout(
    fraction: Float
) = layout { measurable, constraints ->

    // 필요한 값 측정
    val placeable = measurable.measure(constraints)

    val x = -(placeable.width * fraction).roundToInt()

    // layout 메서드 호출, placeable 값으로부터 높이와 폭 전달
    // 자식의 위치를 지정하는 layout() 메서드에 후행 람다 전달
    layout(
        placeable.width,
        placeable.height
    ) {
        placeable.placeRelative(x = x, y = 0)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    Chapter27Theme {
        MainScreen()
    }
}