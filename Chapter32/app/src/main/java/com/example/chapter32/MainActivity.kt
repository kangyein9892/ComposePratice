package com.example.chapter32

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chapter32.ui.theme.Chapter32Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter32Theme {
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
fun Greeting(name: String){
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit){
        coroutineScope.launch() {
            performSlowTask()
        }
    }
}

@Composable
fun CoroutineButton() {
   val coroutineScope = rememberCoroutineScope()

    Button(onClick = {
        coroutineScope.launch{
            performSlowTask()
        }
    }){
        Text(text = "Click Me")
    }
}

suspend fun performSlowTask(){
    println("performSlowwTask before")
    delay(5000) // 많은 시간이 소요되는 태스크 시뮬레이션
    println("performSlowTask after")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chapter32Theme {

    }
}