package com.example.koinexample.data.repository

import com.example.koinexample.data.reponse.LoginResponse
import com.example.koinexample.data.request.LoginRequestBody
import com.example.koinexample.networking.AuthService
import retrofit2.Call
import retrofit2.Callback

class LoginRepositoryImpl(private val authService: AuthService) : LoginRepository {
  override fun loginUser(body: LoginRequestBody) = authService.login(body)
}