package com.example.webcomposetest

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.webcomposetest.models.Student
import com.example.webcomposetest.utils.SampleData
import org.jetbrains.compose.resources.painterResource

import webcomposetest.composeapp.generated.resources.Res
import webcomposetest.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            /*Button(onClick = { showContent = !showContent }) {
                Text("Click here!")
            }

            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }*/

            StudentList(SampleData.students)
        }
    }
}

@Composable
private fun StudentList(students: List<Student>) {
    LazyColumn(Modifier.fillMaxWidth()) {
        items(students) { student ->
            StudentListItem(student)
        }
    }
}

@Composable
private fun StudentListItem(student: Student) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(student.name)
    }
}