package kr.co.lion.chapter50

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.Flow
import kr.co.lion.chapter50.ui.theme.Chapter50Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.reduce
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter50Theme {
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
fun ScreenSetup(viewModel: DemoViewModel = viewModel()){
    MainScreen(viewModel)
}

@Composable
fun MainScreen(viewModel: DemoViewModel){

    var count by remember { mutableStateOf<Int>(0) }

    LaunchedEffect(Unit) {

        // 하나의 flow 연결, 동기적 실행
        viewModel.myFlow
            .flatMapConcat { viewModel.doubleIt(it) }
            .collect{ count = it }

        // 비동기적 수집
        viewModel.myFlow
            .flatMapMerge { viewModel.doubleIt(it) }
            .collect {
                count = it
                println("Count = $it")
            }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$count", style = TextStyle(fontSize = 40.sp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    Chapter50Theme {
        ScreenSetup(viewModel())
    }
}