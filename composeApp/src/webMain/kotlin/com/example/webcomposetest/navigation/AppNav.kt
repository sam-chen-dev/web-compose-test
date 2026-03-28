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
data object StudentDetail : NavKey

@Composable
fun EntryProviderScope<NavKey>.StudentListEntry(backStack: SnapshotStateList<NavKey>) {
    entry<StudentList> {
        StudentListScreen(
            onGoClick = { backStack.add(StudentDetail) }
        )
    }
}

@Composable
fun EntryProviderScope<NavKey>.StudentDetailEntry(backStack: SnapshotStateList<NavKey>) {
    entry<StudentDetail> {
        StudentDetailScreen(
            onBackTrigger = { backStack.removeLastOrNull() }
        )
    }
}