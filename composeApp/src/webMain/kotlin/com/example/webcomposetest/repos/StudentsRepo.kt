package com.example.webcomposetest.repos

import com.example.webcomposetest.models.Student

interface StudentsRepo {
    suspend fun getStudents(): List<Student>

    /*suspend fun getStudent(id: Long): Student

    suspend fun addStudent(student: Student): String

    suspend fun addStudents(students: List<Student>): String

    suspend fun updateStudent(student: Student): String

    suspend fun deleteStudent(id: Long): String*/
}