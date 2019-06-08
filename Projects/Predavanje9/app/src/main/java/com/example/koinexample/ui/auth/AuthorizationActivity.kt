package com.example.koinexample.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.koinexample.R
import com.example.koinexample.common.showFragment
import com.example.koinexample.ui.auth.login.LoginFragment

class AuthorizationActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_authorization)
    
    showFragment(R.id.authFragmentContainer, LoginFragment(), false)
  }
}
