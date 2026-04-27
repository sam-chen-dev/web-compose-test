package com.example.webcomposetest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.webcomposetest.features.orderConfimation.OrderConfirmationScreen
import com.example.webcomposetest.features.orderList.OrderListScreen
import com.example.webcomposetest.features.purchasePapers.PurchasePapersScreen
import com.example.webcomposetest.features.studentDetail.StudentDetailScreen
import com.example.webcomposetest.features.studentList.StudentListScreen
import kotlinx.serialization.Serializable

@Serializable
data object StudentList : NavKey

@Serializable
data class StudentDetail(val id: Long) : NavKey

@Serializable
data object PurchasePapers : NavKey

@Serializable
data class OrderConfirmation(val id: String) : NavKey

@Serializable
data object OrderList : NavKey

@Composable
fun EntryProviderScope<NavKey>.StudentListEntry(backStack: SnapshotStateList<NavKey>) {
    entry<StudentList> {
        StudentListScreen(
            onStudentClick = { id -> backStack.add(StudentDetail(id)) },
            onAddClick = { backStack.add(StudentDetail(-1L)) },
            onPayClick = { backStack.add(PurchasePapers) }
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

@Composable
fun EntryProviderScope<NavKey>.PurchasePapersEntry(backStack: SnapshotStateList<NavKey>) {
    entry<PurchasePapers> {
        PurchasePapersScreen(
            onNavigateToOrderConfirmationTrigger = { id -> backStack.add(OrderConfirmation(id)) }
        )
    }
}

@Composable
fun EntryProviderScope<NavKey>.OrderConfirmationEntry(backStack: SnapshotStateList<NavKey>) {
    entry<OrderConfirmation> { key ->
        OrderConfirmationScreen(
            id = key.id,
            onBackToHomeClick = { backStack.removeLastOrNull() }
        )
    }
}

@Composable
fun EntryProviderScope<NavKey>.OrderListEntry(backStack: SnapshotStateList<NavKey>) {
    entry<OrderList> {
        OrderListScreen()
    }
}