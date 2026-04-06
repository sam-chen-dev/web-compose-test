package com.example.webcomposetest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.webcomposetest.features.studentDetail.StudentDetailScreen
import com.example.webcomposetest.features.studentList.StudentListScreen
import kotlinx.serialization.Serializable

@Serializable
data object StudentList : NavKey

@Serializable
data class StudentDetail(val id: Long) : NavKey

@Composable
fun EntryProviderScope<NavKey>.StudentListEntry(backStack: SnapshotStateList<NavKey>) {
    entry<StudentList> {
        StudentListScreen(
            onStudentClick = { id -> backStack.add(StudentDetail(id)) }
        )
    }
}

@Composable
fun EntryProviderScope<NavKey>.StudentDetailEntry(backStack: SnapshotStateList<NavKey>) {
    entry<StudentDetail> { key ->
        StudentDetailScreen(
            id = key.id,
            onBackTrigger = { backStack.removeLastOrNull() }
        )
    }
}