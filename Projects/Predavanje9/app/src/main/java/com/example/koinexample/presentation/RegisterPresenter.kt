package com.example.koinexample.presentation

import com.example.koinexample.common.isValidEmail
import com.example.koinexample.common.isValidName
import com.example.koinexample.common.isValidPassword
import com.example.koinexample.data.reponse.RegisterResponse
import com.example.koinexample.data.request.RegisterRequestBody
import com.example.koinexample.domain.registration.RegisterUseCase
import com.example.koinexample.ui.auth.register.RegisterContract

class RegisterPresenter(private val registerUseCase: RegisterUseCase) : RegisterContract.Presenter {
  private lateinit var view: RegisterContract.View
  private var isFormValid = true
  
  override fun setView(view: RegisterContract.View) {
    this.view = view
  }
  
  override fun validateInput(name: String, email: String, password: String) {
    if (!name.isValidName()) isFormValid = false
    if (!email.isValidEmail()) isFormValid = false
    if (!password.isValidPassword()) isFormValid = false
    if (isFormValid) {
      registerUseCase.execute(RegisterRequestBody(name, email, password), ::onRegisterSuccess, ::onRegisterFailure)
    } else {
      view.showError("Invalid input data")
    }
  }
  
  private fun onRegisterSuccess(response: RegisterResponse) = view.goToLogin()
  
  private fun onRegisterFailure(error: Throwable) = view.showError(error.localizedMessage)
}