package com.example.koinexample.utils.robots

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.koinexample.R
import com.example.koinexample.utils.VALID_PASSWORD
import com.example.koinexample.utils.waitFor
import java.util.*
import java.util.concurrent.TimeUnit

class RegisterRobot : ScreenRobot<RegisterRobot>() {

    /**
     * Creates a new account, and returns the email and password used.
     * */
    fun createNewAccount(): Pair<String, String> {
        val currentTimestamp = Date().time
        val newEmail = "test$currentTimestamp@test.com"
        val password = VALID_PASSWORD
        val name = "Filip"

        /** Input data */
        onView(withId(R.id.nameInput)).perform(typeText(name))
        onView(withId(R.id.emailInput)).perform(typeText(newEmail))
        onView(withId(R.id.passwordInput)).perform(typeText(password))

        /** Attempt to register */
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.register)).perform(click())

        /** Check if we're on Login now */
        onView(isRoot()).perform(waitFor(500, TimeUnit.MILLISECONDS))

        return newEmail to password
    }
}