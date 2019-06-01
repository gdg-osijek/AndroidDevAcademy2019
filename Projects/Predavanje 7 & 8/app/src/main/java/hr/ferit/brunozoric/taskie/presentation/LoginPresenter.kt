package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.common.RESPONSE_OK
import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.LoginResponse
import hr.ferit.brunozoric.taskie.networking.interactors.TaskieInteractor
import hr.ferit.brunozoric.taskie.prefs.SharedPrefsHelper
import hr.ferit.brunozoric.taskie.ui.login.LoginContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private val interactor: TaskieInteractor, private val prefs: SharedPrefsHelper) :
    LoginContract.Presenter {

    private lateinit var view: LoginContract.View

    override fun setView(view: LoginContract.View) {
        this.view = view
    }

    override fun onUserLogin(user: UserDataRequest) {
        interactor.login(user, loginCallback())
    }

    private fun loginCallback(): Callback<LoginResponse> = object : Callback<LoginResponse> {
        override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
            //TODO : handle default error 400 , 404, 500
        }

        override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>) {
            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse(response.body())
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleOkResponse(loginResponse: LoginResponse?) {
        loginResponse?.token?.let { prefs.storeUserToken( it) }
        view.onLoginSuccesfull()
    }

    private fun handleSomethingWentWrong() {
        view.onLoginFailed()
    }
}