package hr.ferit.brunozoric.taskie.ui.login

import hr.ferit.brunozoric.taskie.model.request.UserDataRequest
import hr.ferit.brunozoric.taskie.ui.base.BasePresenter

interface LoginContract {

    interface View {

        fun onLoginSuccesfull()

        fun onLoginFailed()

    }

    interface Presenter : BasePresenter<View> {

        fun onUserLogin(user: UserDataRequest)
    }
}