package kr.co.lion.chapter50

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

class DemoViewModel: ViewModel() {

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    fun increaseValue(){
        _stateFlow.value += 1
    }

    val myFlow: Flow<Int> = flow {
        for (i in 1..5){
            delay(1000)
            emit(i)
        }
    }

    fun doubleIt(value: Int) = flow {
        emit(value)
        delay(1000)
        emit(value + value)
    }

    val myFlow2 = flowOf(2, 4, 6, 8)
    val myArrayFlow = arrayOf<String>("Red", "Greem", "Blue").asFlow()

    val newFlow = myFlow
        /*.filter {
            it % 2 == 0
        }*/
        .map {
            "Current value = $it"
        }

    /*val newFlow = myFlow
        .transform {
            emit("Value = $it")
            delay(1000)
            val doubled = it * 2
            emit("Value doubled = $doubled")
        }*/
}