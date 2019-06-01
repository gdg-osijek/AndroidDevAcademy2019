package hr.ferit.brunozoric.taskie.ui.register

import hr.ferit.brunozoric.taskie.model.request.UserDataRequest

interface RegisterContract {

    interface View{

        fun onRegisterSuccesfull()

        fun onRegisterFailed()

    }

    interface Presenter{

        fun setView(view: RegisterContract.View)

        fun onRegisterClicked(user: UserDataRequest)

    }

}