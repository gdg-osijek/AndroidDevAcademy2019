package com.example.koinexample.utils.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.koinexample.R

class LoginErrorRobot : ScreenRobot<LoginErrorRobot>() {

    fun checkLoginError() {
        onView(ViewMatchers.withId(R.id.errorTextTest)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.errorTextTest)).check(ViewAssertions.matches(ViewMatchers.hasTextColor(R.color.errorColor)))
    }
}