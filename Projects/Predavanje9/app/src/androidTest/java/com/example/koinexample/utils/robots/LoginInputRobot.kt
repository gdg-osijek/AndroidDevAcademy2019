package com.example.koinexample.utils.robots

import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import com.example.koinexample.R
import com.example.koinexample.utils.VALID_EMAIL
import com.example.koinexample.utils.VALID_PASSWORD

class LoginInputRobot : ScreenRobot<LoginInputRobot>() {

    fun inputValidCredentials() {
        inputCredentials(VALID_EMAIL, VALID_PASSWORD)
    }

    fun inputCredentials(email: String, password: String) {
        enterTextIntoView(R.id.emailInput, email)
        enterTextIntoView(R.id.passwordInput, password)
        closeSoftKeyboard()
    }
}