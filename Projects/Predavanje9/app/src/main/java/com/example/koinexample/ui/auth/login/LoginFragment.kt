package com.example.koinexample.ui.auth.login

import com.example.koinexample.R
import com.example.koinexample.common.showFragment
import com.example.koinexample.common.toast
import com.example.koinexample.ui.auth.register.RegisterFragment
import com.example.koinexample.ui.base.BaseFragment
import com.example.koinexample.ui.feed.startFeedActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment(), LoginContract.View {
  private val presenter by inject<LoginContract.Presenter>()
  
  override fun getLayout() = R.layout.fragment_login
  
  override fun initUi() {
    presenter.setView(this)
    
    login.setOnClickListener { presenter.validateInput(emailInput.text.toString(), passwordInput.text.toString()) }
    register.setOnClickListener {
      activity?.showFragment(R.id.authFragmentContainer, RegisterFragment(), false)
    }
  }
  
  override fun showError(error: String) = toast(error)
  
  override fun goToFeed() {
    activity?.let { startFeedActivity(it) }
    activity?.finish()
  }
}