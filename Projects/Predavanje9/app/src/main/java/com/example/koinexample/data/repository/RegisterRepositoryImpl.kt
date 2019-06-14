package com.example.koinexample.data.repository

import com.example.koinexample.data.request.RegisterRequestBody
import com.example.koinexample.networking.AuthService

class RegisterRepositoryImpl(private val authService: AuthService) : RegisterRepository {
  override fun registerUser(body: RegisterRequestBody) = authService.register(body)
}