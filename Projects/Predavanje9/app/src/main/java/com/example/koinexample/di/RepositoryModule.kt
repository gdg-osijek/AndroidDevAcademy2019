package com.example.koinexample.di

import com.example.koinexample.data.repository.LoginRepository
import com.example.koinexample.data.repository.LoginRepositoryImpl
import com.example.koinexample.data.repository.RegisterRepository
import com.example.koinexample.data.repository.RegisterRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
  factory<LoginRepository> { LoginRepositoryImpl(get()) }
  factory<RegisterRepository> { RegisterRepositoryImpl(get()) }
}