package com.example.webcomposetest.features.studentList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StudentListViewModel : ViewModel() {
    private val uiScope = viewModelScope
    private val _name = MutableStateFlow("Samhahaha")

    val name = _name.asStateFlow()

    init {

    }

    fun updateName(name: String) = _name.update { name }
}