package com.example.webcomposetest.features.studentList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webcomposetest.models.Student
import com.example.webcomposetest.repos.StudentsRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudentListViewModel(private val studentsRepo: StudentsRepoImpl) : ViewModel() {
    private val uiScope = viewModelScope
    private val _students = MutableStateFlow<List<Student>>(emptyList())

    val students = _students.asStateFlow()

    init {
        refreshStudents()
    }

    fun refreshStudents() = uiScope.launch {
        try {
            _students.update { studentsRepo.getStudents() }
        } catch (e: Exception) {
            println("Error: ${e.message.toString()}")
        }
    }
}