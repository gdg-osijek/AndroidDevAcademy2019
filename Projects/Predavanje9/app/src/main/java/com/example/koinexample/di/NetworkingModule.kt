package com.example.koinexample.di

import com.example.koinexample.BuildConfig
import com.example.koinexample.networking.AuthService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://authenticationexample.herokuapp.com/"
const val KEY_AUTHORIZATION = "authorization"
const val AUTHORIZATION_VALUE = "testbackend"
const val LOGGING_INTERCEPTOR = "logging"
const val AUTH_INTERCEPTOR = "auth"

val networkingModule = module {
  
  //GSON
  single { GsonConverterFactory.create() as Converter.Factory }
  
  //INTERCEPTORS
  single(named(LOGGING_INTERCEPTOR)) {
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
  }
  single(named(AUTH_INTERCEPTOR)) {
    Interceptor {
      val request = it.request().newBuilder().addHeader(KEY_AUTHORIZATION, AUTHORIZATION_VALUE)
        .build()
      it.proceed(request)
    }
  }
  
  //OKHTTPCLIENT
  single {
    OkHttpClient.Builder().apply {
      if (BuildConfig.DEBUG) addInterceptor(get(named(LOGGING_INTERCEPTOR)))
      readTimeout(1L, TimeUnit.MINUTES)
      connectTimeout(1L, TimeUnit.MINUTES)
    }.build()
  }
  
  //RETROFIT
  single {
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(get())
      .addConverterFactory(get())
      .build()
  }
  
  single { get<Retrofit>().create(AuthService::class.java) }
}