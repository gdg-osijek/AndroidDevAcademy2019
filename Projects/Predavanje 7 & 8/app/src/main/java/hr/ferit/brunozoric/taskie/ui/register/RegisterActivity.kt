package hr.ferit.brunozoric.taskie.ui.register

import android.content.Intent
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.RESPONSE_OK
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.common.onClick
import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.RegisterResponse
import hr.ferit.brunozoric.taskie.networking.BackendFactory
import hr.ferit.brunozoric.taskie.presentation.RegisterPresenter
import hr.ferit.brunozoric.taskie.ui.base.BaseActivity
import hr.ferit.brunozoric.taskie.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : BaseActivity(), RegisterContract.View {

    private val presenter: RegisterContract.Presenter by lazy { RegisterPresenter(BackendFactory.getTaskieInteractor()) }

    override fun getLayoutResourceId(): Int = R.layout.activity_register

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun setUpUi() {
        register.onClick { signInClicked() }
        goToLogin.onClick { goToLoginClicked() }
    }

    private fun signInClicked() {
        presenter.onRegisterClicked(
            UserDataRequest(
                name = name.text.toString(),
                email = email.text.toString(),
                password = password.text.toString()
            )
        )
    }

    private fun goToLoginClicked() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


    override fun onRegisterSuccesfull() {
        this.displayToast("Successfully registered")
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onRegisterFailed() {
        displayToast("Something went wrong!")
    }
}