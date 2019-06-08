package com.example.koinexample.networking

import com.example.koinexample.data.reponse.LoginResponse
import com.example.koinexample.data.reponse.RegisterResponse
import com.example.koinexample.data.request.LoginRequestBody
import com.example.koinexample.data.request.RegisterRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
  
  @POST(LOGIN_URL)
  fun login(@Body loginBody: LoginRequestBody): Call<LoginResponse>
  
  @POST(REGISTER_URL)
  fun register(@Body registerBody: RegisterRequestBody): Call<RegisterResponse>
}