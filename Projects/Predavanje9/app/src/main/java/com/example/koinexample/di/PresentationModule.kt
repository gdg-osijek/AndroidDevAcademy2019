package com.example.koinexample.di

import com.example.koinexample.presentation.LoginPresenter
import com.example.koinexample.presentation.RegisterPresenter
import com.example.koinexample.ui.auth.login.LoginContract
import com.example.koinexample.ui.auth.register.RegisterContract
import org.koin.dsl.module

val presentationModule = module {
  factory<LoginContract.Presenter> { LoginPresenter(get()) }
  factory<RegisterContract.Presenter> { RegisterPresenter(get()) }
}