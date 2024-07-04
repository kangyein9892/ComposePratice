package kr.co.lion.chapter25

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.co.lion.chapter25.ui.theme.Chapter25Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter25Theme {
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
    Row {
        Text(
            text = "Large Text\nMore Text",
            Modifier.alignBy(LastBaseline),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Small Text",
            modifier = Modifier.paddingFrom(
                aligmentLine = FirstBaseline,
                before = 80.dp, after = 0.dp
            ),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }

   Row(verticalAlignment = Alignment.CenterVertically,
       modifier = Modifier.size(width = 400.dp, height = 200.dp))
      {
       TextCell("1", Modifier.weight(weight = 0.2f, fill = true))
       TextCell("2", Modifier.weight(weight = 0.4f, fill = true))
       TextCell("3", Modifier.weight(weight = 0.3f, fill = true))
   }

    Row(modifier = Modifier.height(300.dp))
    {
        TextCell("1", Modifier.align(Alignment.Top))
        TextCell("2", Modifier.align(Alignment.CenterVertically))
        TextCell("3", Modifier.align(Alignment.Bottom))
    }

    Column(horizontalAlignment = Alignment.End,
        modifier = Modifier.width(250.dp)) {
        TextCell("1")
        TextCell("2")
        TextCell("3")
    }

    Column {
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.width(1000.dp)) {
            Column(verticalArrangement = Arrangement.Bottom) {
                TextCell("1")
                TextCell("2")
                TextCell("3")
            }
            Column {
                TextCell("4")
                TextCell("5")
                TextCell("6")
            }
            Column {
                TextCell("7")
                TextCell("8")
            }
        }

        Row(horizontalArrangement = Arrangement.End) {
            TextCell("9")
            TextCell("10")
            TextCell("11")
        }
    }
}

@Composable
fun TextCell(text: String, modifier: Modifier = Modifier) {
    val cellModifier = Modifier
        .padding(4.dp)
        .size(100.dp, 100.dp)
        .border(width = 4.dp, color = Color.Black)

    Text(text = text,
        cellModifier.then(modifier),
        fontSize = 70.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center)
}