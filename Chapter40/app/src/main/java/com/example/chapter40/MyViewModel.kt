package com.example.chapter40

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    var customerCount by mutableStateOf(0)

    fun increaseCount() {
        customerCount++
    }
}