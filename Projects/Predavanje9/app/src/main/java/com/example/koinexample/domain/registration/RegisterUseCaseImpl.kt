package com.example.koinexample.domain.registration

import com.example.koinexample.common.ErrorLambda
import com.example.koinexample.common.SuccessLambda
import com.example.koinexample.data.reponse.RegisterResponse
import com.example.koinexample.data.repository.RegisterRepository
import com.example.koinexample.data.request.RegisterRequestBody
import com.example.koinexample.networking.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterUseCaseImpl(private val registerRepository: RegisterRepository) : RegisterUseCase {
  override fun execute(body: RegisterRequestBody, onSuccess: SuccessLambda<RegisterResponse>, onFailure: ErrorLambda) {
    registerRepository.registerUser(body).enqueue(object : Callback<RegisterResponse> {
      override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
        onFailure(t)
      }
  
      override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
        if (response.isSuccessful) response.body()?.run(onSuccess)
      }
    })
  }
}