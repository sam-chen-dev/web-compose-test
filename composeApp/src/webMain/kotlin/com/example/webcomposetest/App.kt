package com.example.webcomposetest

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import com.example.webcomposetest.navigation.NavDisplay
import com.example.webcomposetest.navigation.StudentDetail
import com.example.webcomposetest.navigation.StudentList
import com.github.terrakok.navigation3.browser.ChronologicalBrowserNavigation
import com.github.terrakok.navigation3.browser.buildBrowserHistoryFragment
import com.github.terrakok.navigation3.browser.getBrowserHistoryFragmentName

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
}