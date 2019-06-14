package com.example.koinexample.domain.login

import com.example.koinexample.common.ErrorLambda
import com.example.koinexample.common.SuccessLambda
import com.example.koinexample.data.reponse.LoginResponse
import com.example.koinexample.data.request.LoginRequestBody

interface LoginUseCase {
  
  fun execute(body: LoginRequestBody, onSuccess: SuccessLambda<LoginResponse>, onFailure: ErrorLambda)
}