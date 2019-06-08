package com.example.koinexample.domain.registration

import com.example.koinexample.common.ErrorLambda
import com.example.koinexample.common.SuccessLambda
import com.example.koinexample.data.reponse.RegisterResponse
import com.example.koinexample.data.request.RegisterRequestBody

interface RegisterUseCase {
  
  fun execute(body: RegisterRequestBody, onSuccess: SuccessLambda<RegisterResponse>, onFailure: ErrorLambda)
}