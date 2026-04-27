package com.example.webcomposetest.di

import com.example.webcomposetest.features.orderConfimation.OrderConfirmationViewModel
import com.example.webcomposetest.features.purchasePapers.PurchasePapersViewModel
import com.example.webcomposetest.features.studentDetail.StudentDetailViewModel
import com.example.webcomposetest.features.studentList.StudentListViewModel
import com.example.webcomposetest.repos.OrdersRepoImpl
import com.example.webcomposetest.repos.PaymentRepoImpl
import com.example.webcomposetest.repos.ProductsRepoImpl
import com.example.webcomposetest.repos.StudentsRepoImpl
import com.example.webcomposetest.services.NyAcgGalaService
import com.example.webcomposetest.services.OrdersApi
import com.example.webcomposetest.services.PaymentApi
import com.example.webcomposetest.services.ProductsApi
import com.example.webcomposetest.services.StudentsApi
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    /*Managers*/

    /*Services*/
    single { NyAcgGalaService.httpClient }
    single { StudentsApi(get()) }
    single { ProductsApi(get()) }
    single { OrdersApi(get()) }
    single { PaymentApi(get()) }

    /*Database*/

    /*Repos*/
    single { StudentsRepoImpl(get()) }
    single { ProductsRepoImpl(get()) }
    single { OrdersRepoImpl(get()) }
    single { PaymentRepoImpl(get()) }

    /*ViewModels*/
    viewModel { StudentListViewModel(get()) }
    viewModel { params -> StudentDetailViewModel(params[0], get()) }
    viewModel { PurchasePapersViewModel(get(), get(), get()) }
    viewModel { params -> OrderConfirmationViewModel(params[0], get()) }
}