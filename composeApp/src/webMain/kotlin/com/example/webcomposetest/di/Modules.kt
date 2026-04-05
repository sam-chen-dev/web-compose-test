package com.example.webcomposetest.di

import com.example.webcomposetest.features.studentDetail.StudentDetailViewModel
import com.example.webcomposetest.features.studentList.StudentListViewModel
import com.example.webcomposetest.repos.StudentsRepoImpl
import com.example.webcomposetest.services.NyAcgGalaService
import com.example.webcomposetest.services.StudentsApi
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    /*Managers*/

    /*Services*/
    single { NyAcgGalaService.httpClient }
    single { StudentsApi(get()) }

    /*Database*/

    /*Repos*/
    single { StudentsRepoImpl(get()) }

    /*ViewModels*/
    viewModel { StudentListViewModel(get()) }
    viewModel { StudentDetailViewModel() }
}