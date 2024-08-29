package kr.co.lion.chapter50

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class DemoViewModel: ViewModel() {

    val myFlow: Flow<Int> = flow {
        // 생산자 블록
        for (i in 0..9){
            emit(i)
            delay(2000)
        }
    }

    val myFlow2 = flowOf(2, 4, 6, 8)
    val myArrayFlow = arrayOf<String>("Red", "Greem", "Blue").asFlow()

    val newFlow = myFlow.map {
        "Current value = $it"
    }
}