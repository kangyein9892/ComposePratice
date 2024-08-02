package com.example.chapter40

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    var customerCount by mutableStateOf(0)
    var customerName: MutableLiveData<String> = MutableLiveData("")

    fun setName(name: String){
        customerName.value = name
    }

    fun increaseCount() {
        customerCount++
    }
}