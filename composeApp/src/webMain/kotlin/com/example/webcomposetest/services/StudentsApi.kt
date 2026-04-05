package com.example.webcomposetest.services

import com.example.webcomposetest.models.Student
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class StudentsApi(private val httpClient: HttpClient) {
    suspend fun getStudents(): HttpResponse {
        return httpClient.get("students")
    }

    suspend fun getStudent(id: Long): HttpResponse {
        return httpClient.get("students/$id")
    }

    suspend fun addStudent(student: Student): HttpResponse {
        return httpClient.post("students") {
            setBody(student)
        }
    }

    suspend fun addStudents(students: List<Student>): HttpResponse {
        return httpClient.post("students-batch") {
            setBody(students)
        }
    }

    suspend fun updateStudent(student: Student): HttpResponse {
        return httpClient.put("students") {
            setBody(student)
        }
    }

    suspend fun deleteStudent(id: Long): HttpResponse {
        return httpClient.delete("students/$id")
    }
}