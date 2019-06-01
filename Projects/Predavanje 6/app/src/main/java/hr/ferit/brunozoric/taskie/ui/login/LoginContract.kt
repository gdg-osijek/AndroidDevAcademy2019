package hr.ferit.brunozoric.taskie.ui.login

import hr.ferit.brunozoric.taskie.model.request.UserDataRequest

interface LoginContract {

    interface View{

        fun onLoginSuccesfull()

        fun onLoginFailed()

    }

    interface Presenter{

        fun setView(view: LoginContract.View)

        fun onUserLogin(user: UserDataRequest)

    }

}