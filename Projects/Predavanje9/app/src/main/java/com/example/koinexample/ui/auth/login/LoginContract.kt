package com.example.koinexample.ui.auth.login

interface LoginContract {
  
  interface View {
    fun showError(error: String)
    
    fun goToFeed()
  }
  
  interface Presenter {
    fun setView(view: View)
    
    fun validateInput(email: String, password: String)
  }
}