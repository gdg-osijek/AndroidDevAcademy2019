package com.example.koinexample.data.repository

import com.example.koinexample.data.reponse.RegisterResponse
import com.example.koinexample.data.request.RegisterRequestBody
import retrofit2.Call

interface RegisterRepository {
  
  fun registerUser(body: RegisterRequestBody): Call<RegisterResponse>
}