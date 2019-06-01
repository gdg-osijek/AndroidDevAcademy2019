package hr.ferit.brunozoric.taskie.ui.login

import android.content.Intent
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.common.onClick
import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.networking.BackendFactory
import hr.ferit.brunozoric.taskie.prefs.provideSharedPrefs
import hr.ferit.brunozoric.taskie.presentation.LoginPresenter
import hr.ferit.brunozoric.taskie.ui.base.BaseActivity
import hr.ferit.brunozoric.taskie.ui.register.RegisterActivity
import hr.ferit.brunozoric.taskie.ui.tasklist.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View {

    private val presenter: LoginContract.Presenter by lazy {
        LoginPresenter(
            BackendFactory.getTaskieInteractor(),
            provideSharedPrefs()
        )
    }

    override fun getLayoutResourceId(): Int = R.layout.activity_login

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun setUpUi() {
        login.onClick { signInClicked() }
        goToLogin.onClick { goToRegistrationClicked() }
    }

    private fun signInClicked() {
        presenter.onUserLogin(UserDataRequest(email.text.toString(), password.text.toString()))
    }


    private fun goToRegistrationClicked() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }


    override fun onLoginSuccesfull() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onLoginFailed() {
        displayToast("Fail")
    }
}