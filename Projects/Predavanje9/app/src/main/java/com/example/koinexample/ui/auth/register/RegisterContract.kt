package com.example.koinexample.ui.auth.register

interface RegisterContract {
  
  interface View {
    fun showError(message: String)
    
    fun goToLogin()
  }
  
  interface Presenter {
    fun setView(view: View)
    
    fun validateInput(name: String, email: String, password: String)
  }
}