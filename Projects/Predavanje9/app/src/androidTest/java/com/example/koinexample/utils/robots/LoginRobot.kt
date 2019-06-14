package com.example.koinexample.utils.robots

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.koinexample.R
import com.example.koinexample.utils.waitFor
import java.util.concurrent.TimeUnit

open class LoginRobot : ScreenRobot<LoginRobot>() {

    fun performLogin() {
        closeSoftKeyboard()
        onView(ViewMatchers.withId(R.id.login)).perform(ViewActions.click())
        onView(ViewMatchers.isRoot()).perform(waitFor(500, TimeUnit.MILLISECONDS))
    }
}