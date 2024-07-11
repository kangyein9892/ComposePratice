package kr.co.lion.chapter30

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import kr.co.lion.chapter30.ui.theme.Chapter30Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter30Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    ConstraintLayout(Modifier.size(width = 350.dp, height = 220.dp)) {
        val (button1, button2, button3) = createRefs()

        // 수직 배리어, button1과 button2 끝 가장자리에서 30dp 거리에 위치
        // 만약 폭이 다를 경우 두 컴포넌트 중 폭이 넓은 컴포넌트 끝 가장자리에서 30dp 위치
        val barrier = createEndBarrier(button1, button2)

        MyButton(text = "Button1", Modifier.width(100.dp).constrainAs(button1) {
            top.linkTo(parent.top, margin = 30.dp)
            start.linkTo(parent.start, margin = 8.dp)
        })

        MyButton(text = "Button2", Modifier.width(150.dp).constrainAs(button2) {
            top.linkTo(button1.bottom, margin = 20.dp)
            start.linkTo(parent.start, margin = 8.dp)
        })

        MyButton(text = "Button3", Modifier.width(100.dp).constrainAs(button3) {
            linkTo(parent.top, parent.bottom,
                topMargin = 8.dp, bottomMargin = 8.dp)
            linkTo(button1.end, parent.end,
                startMargin = 30.dp, endMargin = 8.dp)

            // button3 컴포넌트 크기는 제약이 허용하는 최대공간 채우도록 설정
            // 버튼은 높이 채우고 동시에 button1, button2 크기를 변경함에 따라 폭 조정
            // 이를 위해서는 button3 폭과 높이 제약은 fillConstraints 로 변경
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints

            // 처음 건 button3이 button1의 변화에 의한 제약은 받지만, button2의 변화에 의한 제약은 받지 않음
            // -> button1, button2 끝 가장자리에 배리어 만들고, button1 끝 가장자리 기준으로 button3의 시작 모서리에 제약 적용하는 대신, 배리어를 기준으로 시작 모서리에 제약 적용
            // 두 버튼의 폭이 늘어나면 button3을 제약하는 캐리어가 이동하므로 결과적으로 button3의 폭이 줄어든다
            start.linkTo(barrier, margin = 30.dp)
        })
    }
}

@Composable
fun MyButton(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        modifier = modifier
    ) {
        Text(text)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    Chapter30Theme {
        MainScreen()
    }
}