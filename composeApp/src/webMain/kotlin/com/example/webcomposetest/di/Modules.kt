package com.example.webcomposetest.di

import com.example.webcomposetest.features.studentDetail.StudentDetailViewModel
import com.example.webcomposetest.features.studentList.StudentListViewModel
import com.example.webcomposetest.utils.Animal
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Animal() }

    viewModel { StudentListViewModel() }
    viewModel { StudentDetailViewModel() }
}