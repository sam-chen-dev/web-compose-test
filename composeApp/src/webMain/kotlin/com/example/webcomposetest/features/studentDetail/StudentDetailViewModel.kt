package com.example.webcomposetest.features.studentDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StudentDetailViewModel : ViewModel() {
    private val uiScope = viewModelScope
    private val _number = MutableStateFlow(11)

    val number = _number.asStateFlow()

    init {

    }
}