package com.example.koinexample.di

import com.example.koinexample.domain.login.LoginUseCase
import com.example.koinexample.domain.login.LoginUseCaseImpl
import com.example.koinexample.domain.registration.RegisterUseCase
import com.example.koinexample.domain.registration.RegisterUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
  factory<LoginUseCase> { LoginUseCaseImpl(get()) }
  factory<RegisterUseCase> { RegisterUseCaseImpl(get()) }
}