package com.example.koinexample.presentation

import com.example.koinexample.common.isValidEmail
import com.example.koinexample.common.isValidPassword
import com.example.koinexample.data.reponse.LoginResponse
import com.example.koinexample.data.request.LoginRequestBody
import com.example.koinexample.domain.login.LoginUseCase
import com.example.koinexample.ui.auth.login.LoginContract

class LoginPresenter(private val loginUseCase: LoginUseCase) : LoginContract.Presenter {
  private lateinit var view: LoginContract.View
  private var formValid = true
  
  override fun setView(view: LoginContract.View) {
    this.view = view
  }
  
  override fun validateInput(email: String, password: String) {
    if (!email.isValidEmail()) formValid = false
    if (!password.isValidPassword()) formValid = false
    if (formValid) {
      loginUseCase.execute(LoginRequestBody(email, password), ::onLoginSuccess, ::onLoginError)
    } else {
      view.showError("Invalid input data")
    }
  }
  
  private fun onLoginSuccess(response: LoginResponse) = view.goToFeed()
  
  private fun onLoginError(error: Throwable) = view.showError(error.localizedMessage)
}