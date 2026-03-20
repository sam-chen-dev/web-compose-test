package com.example.webcomposetest.utils

import com.example.webcomposetest.models.Student

object SampleData {
    val students = mutableListOf<Student>().apply {
        repeat(50) { number ->
            add(Student(number.toLong(), "Student #$number"))
        }
    }
}