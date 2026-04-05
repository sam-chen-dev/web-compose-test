package com.example.webcomposetest.repos

import com.example.webcomposetest.models.Student
import com.example.webcomposetest.services.StudentsApi
import com.example.webcomposetest.utils.isSuccessful
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText

class StudentsRepoImpl(
    private val studentsApi: StudentsApi
) : StudentsRepo {
    override suspend fun getStudents(): List<Student> {
        val result = studentsApi.getStudents()

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun getStudent(id: Long): Student {
        val result = studentsApi.getStudent(id)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun addStudent(student: Student): String {
        val result = studentsApi.addStudent(student)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun addStudents(students: List<Student>): String {
        val result = studentsApi.addStudents(students)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun updateStudent(student: Student): String {
        val result = studentsApi.updateStudent(student)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun deleteStudent(id: Long): String {
        val result = studentsApi.deleteStudent(id)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }
}