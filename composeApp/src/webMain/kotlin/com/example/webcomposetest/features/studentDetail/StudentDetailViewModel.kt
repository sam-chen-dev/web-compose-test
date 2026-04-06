package com.example.webcomposetest.features.studentDetail

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webcomposetest.models.Student
import com.example.webcomposetest.repos.StudentsRepoImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudentDetailViewModel(
    private val id: Long,
    private val studentsRepo: StudentsRepoImpl
) : ViewModel() {
    private val uiScope = viewModelScope
    private val _student = MutableStateFlow<Student?>(null)
    private val _isSavedSuccessfully = MutableSharedFlow<Boolean>()
    private val _isDeletedSuccessfully = MutableSharedFlow<Boolean>()

    val nameState = TextFieldState()
    val student = _student.asStateFlow()
    val isSavedSuccessfully = _isSavedSuccessfully.asSharedFlow()
    val isDeletedSuccessfully = _isDeletedSuccessfully.asSharedFlow()

    init {
        initStudent()
    }

    private fun initStudent() = uiScope.launch {
        try {
            _student.update { studentsRepo.getStudent(id) }
            nameState.setTextAndPlaceCursorAtEnd(_student.value?.name ?: "")
        } catch (e: Exception) {
            println("Error: ${e.message.toString()}")
        }
    }

    fun saveStudent() = uiScope.launch {
        try {
            _student.update { it?.copy(name = nameState.text.toString()) }

            _student.value?.let { student ->
                val result = studentsRepo.updateStudent(student)
                println("result: $result")

                _isSavedSuccessfully.emit(true)
            }
        } catch (e: Exception) {
            println("Error: ${e.message.toString()}")
            _isSavedSuccessfully.emit(false)
        }
    }

    fun deleteStudent() = uiScope.launch {
        try {
            val result = studentsRepo.deleteStudent(id)
            println("result: $result")

            _isDeletedSuccessfully.emit(true)
        } catch (e: Exception) {
            println("Error: ${e.message.toString()}")
            _isDeletedSuccessfully.emit(false)
        }
    }
}