package com.example.koinexample.data.repository

import com.example.koinexample.data.reponse.LoginResponse
import com.example.koinexample.data.request.LoginRequestBody
import retrofit2.Call

interface LoginRepository {
  
  fun loginUser(body: LoginRequestBody): Call<LoginResponse>
}