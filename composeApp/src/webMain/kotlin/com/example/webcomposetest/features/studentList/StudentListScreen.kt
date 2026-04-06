package com.example.webcomposetest.features.studentList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.webcomposetest.models.Student
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StudentListScreen(
    onStudentClick: (Long) -> Unit
) {
    val viewModel: StudentListViewModel = koinViewModel()
    val students by viewModel.students.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Toolbar()
        StudentList(students, onStudentClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar() {
    TopAppBar(
        title = { Text("Student List") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
private fun StudentList(students: List<Student>, onStudentClick: (Long) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(students) { student ->
            StudentListItem(student, onStudentClick)
        }
    }
}

@Composable
private fun StudentListItem(student: Student, onStudentClick: (Long) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onStudentClick(student.id) }
            .padding(16.dp)
    ) {
        Text(student.name, fontSize = 20.sp)
    }
}