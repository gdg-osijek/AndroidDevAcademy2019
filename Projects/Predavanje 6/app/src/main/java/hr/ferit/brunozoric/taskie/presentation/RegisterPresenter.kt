package hr.ferit.brunozoric.taskie.presentation

import hr.ferit.brunozoric.taskie.common.RESPONSE_OK
import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.model.response.RegisterResponse
import hr.ferit.brunozoric.taskie.networking.interactors.TaskieInteractor
import hr.ferit.brunozoric.taskie.ui.register.RegisterContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter(private val interactor: TaskieInteractor): RegisterContract.Presenter{

    private lateinit var view: RegisterContract.View

    override fun setView(view: RegisterContract.View) {
        this.view = view
    }

    override fun onRegisterClicked(user: UserDataRequest) {
        interactor.register(user, registerCallback())
    }

    private fun registerCallback(): Callback<RegisterResponse> = object : Callback<RegisterResponse> {
        override fun onFailure(call: Call<RegisterResponse>?, t: Throwable?) {
            //TODO : handle default error 400 , 404, 500
        }

        override fun onResponse(call: Call<RegisterResponse>?, response: Response<RegisterResponse>) {
            if (response.isSuccessful) {
                when (response.code()) {
                    RESPONSE_OK -> handleOkResponse()
                    else -> handleSomethingWentWrong()
                }
            }
        }
    }

    private fun handleOkResponse() {
        view.onRegisterSuccesfull()
    }

    private fun handleSomethingWentWrong() {
        view.onRegisterFailed()
    }
}