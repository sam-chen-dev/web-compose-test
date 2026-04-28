package com.example.webcomposetest

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import com.example.webcomposetest.di.appModule
import com.example.webcomposetest.navigation.NavDisplay
import com.example.webcomposetest.navigation.OrderConfirmation
import com.example.webcomposetest.navigation.OrderList
import com.example.webcomposetest.navigation.PurchasePapers
import com.example.webcomposetest.navigation.StudentDetail
import com.example.webcomposetest.navigation.StudentList
import com.github.terrakok.navigation3.browser.ChronologicalBrowserNavigation
import com.github.terrakok.navigation3.browser.buildBrowserHistoryFragment
import com.github.terrakok.navigation3.browser.getBrowserHistoryFragmentName
import com.github.terrakok.navigation3.browser.getBrowserHistoryFragmentParameters
import org.koin.compose.KoinApplication
import org.koin.dsl.KoinConfiguration

@Composable
fun App() = KoinApplication(
    configuration = KoinConfiguration { modules(appModule) },
    content = {
        MaterialTheme {
            val backStack = remember { mutableStateListOf<NavKey>(PurchasePapers) }
            //val backStack = rememberNavBackStack(StudentList)

            ChronologicalBrowserNavigation(
                backStack = backStack,
                saveKey = { key ->
                    when (key) {
                        is StudentList -> buildBrowserHistoryFragment(StudentList.toString())

                        is StudentDetail -> buildBrowserHistoryFragment(
                            StudentDetail.toString(),
                            mapOf("id" to key.id.toString())
                        )

                        is PurchasePapers -> buildBrowserHistoryFragment(PurchasePapers.toString())

                        is OrderConfirmation -> buildBrowserHistoryFragment(
                            OrderConfirmation.toString(),
                            mapOf("id" to key.id)
                        )

                        is OrderList -> buildBrowserHistoryFragment(OrderList.toString())

                        else -> null
                    }
                },
                restoreKey = { fragment ->
                    when (getBrowserHistoryFragmentName(fragment)) {
                        StudentList.toString() -> StudentList

                        StudentDetail.toString() -> StudentDetail(
                            getBrowserHistoryFragmentParameters(fragment).getValue("id")?.toLong()
                                ?: error("id is required")
                        )

                        PurchasePapers.toString() -> PurchasePapers

                        OrderConfirmation.toString() -> OrderConfirmation(
                            getBrowserHistoryFragmentParameters(fragment).getValue("id")
                                ?: error("id is required")
                        )

                        OrderList.toString() -> OrderList

                        else -> null
                    }
                }
            )

            NavDisplay(backStack)
        }
    }
)

/*
@Composable
fun App() {
    MaterialTheme {
        val backStack = remember { mutableStateListOf<NavKey>(StudentList) }
        //val backStack = rememberNavBackStack(StudentList)

        ChronologicalBrowserNavigation(
            backStack = backStack,
            saveKey = { key ->
                when (key) {
                    is StudentList -> buildBrowserHistoryFragment(StudentList.toString())

                    is StudentDetail -> buildBrowserHistoryFragment(StudentDetail.toString())

                    else -> null
                }
            },
            restoreKey = { fragment ->
                when (getBrowserHistoryFragmentName(fragment)) {
                    StudentList.toString() -> StudentList

                    StudentDetail.toString() -> StudentDetail

                    else -> null
                }
            }
        )

        NavDisplay(backStack)
    }
}*/