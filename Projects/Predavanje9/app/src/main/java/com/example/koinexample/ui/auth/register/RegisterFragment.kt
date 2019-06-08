package com.example.koinexample.ui.auth.register

import com.example.koinexample.R
import com.example.koinexample.common.showFragment
import com.example.koinexample.common.toast
import com.example.koinexample.ui.auth.login.LoginFragment
import com.example.koinexample.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.android.ext.android.inject

class RegisterFragment : BaseFragment(), RegisterContract.View {
  private val presenter by inject<RegisterContract.Presenter>()
  
  override fun getLayout() = R.layout.fragment_register
  
  override fun initUi() {
    presenter.setView(this)
    
    login.setOnClickListener { activity?.showFragment(R.id.authFragmentContainer, LoginFragment(), false) }
    register.setOnClickListener {
      presenter.validateInput(nameInput.text.toString(), emailInput.text.toString(),
                              passwordInput.text.toString())
    }
  }
  
  override fun goToLogin() {
    activity?.showFragment(R.id.authFragmentContainer, LoginFragment(), false)
  }
  
  override fun showError(message: String) = toast(message)
}