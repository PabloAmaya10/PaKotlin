package com.pa.pakotlin.di

import com.pa.pakotlin.domain.repository.GetDatabaseRepository
import com.pa.pakotlin.domain.repository.InfoRepository
import com.pa.pakotlin.domain.repository.LoginRepository
import com.pa.pakotlin.domain.repository.SharedPrefRepository
import com.pa.pakotlin.domain.usecases.GetUserDataApiUseCase
import com.pa.pakotlin.domain.usecases.GetUserDatabaseUseCase
import com.pa.pakotlin.domain.usecases.LoginUseCase
import com.pa.pakotlin.domain.usecases.SaveDataSpUseCase
import com.pa.pakotlin.presentation.viewmodel.DashboardViewModel
import com.pa.pakotlin.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModuleLogin = module {
    single { InfoRepository() }
    single { LoginRepository() }
    single { LoginUseCase(get()) }
    single { GetUserDataApiUseCase(get()) }
    viewModel { MainViewModel(get(), get()) }
}

val appModuleDashboard = module {
    single { SharedPrefRepository() }
    single { GetDatabaseRepository() }
    single { SaveDataSpUseCase(get()) }
    single { GetUserDatabaseUseCase(get()) }
    viewModel { DashboardViewModel(get(), get()) }
}
