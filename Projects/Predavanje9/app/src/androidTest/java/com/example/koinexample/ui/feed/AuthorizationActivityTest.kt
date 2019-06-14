package com.example.koinexample.ui.feed

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.koinexample.*
import com.example.koinexample.ui.auth.AuthorizationActivity
import com.example.koinexample.utils.INVALID_PASSWORD
import com.example.koinexample.utils.VALID_EMAIL
import com.example.koinexample.utils.VALID_PASSWORD
import com.example.koinexample.utils.robots.LoginErrorRobot
import com.example.koinexample.utils.robots.LoginInputRobot
import com.example.koinexample.utils.robots.LoginRobot
import com.example.koinexample.utils.robots.RegisterRobot
import com.example.koinexample.utils.robots.ScreenRobot.Companion.withRobot
import com.example.koinexample.utils.waitFor
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit

class AuthorizationActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(AuthorizationActivity::class.java, false, false)

    @Before
    fun setUp() {
        ActivityScenario.launch(AuthorizationActivity::class.java)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun verifyLoginScreenOpen() {
        onView(withId(R.id.emailInput)).check(matches(isDisplayed()))
        onView(withId(R.id.passwordInput)).check(matches(isDisplayed()))
        onView(withId(R.id.login)).check(matches(isDisplayed()))
    }

    @Test
    fun onInvalidCredentialsErrorShown() {
        withRobot(LoginInputRobot::class.java).inputCredentials(VALID_EMAIL, INVALID_PASSWORD)
        withRobot(LoginRobot::class.java).performLogin()
        withRobot(LoginErrorRobot::class.java).checkLoginError()
    }

    @Test
    fun onValidCredentialsShowsFeed() {
        onView(withId(R.id.emailInput)).perform(typeText(VALID_EMAIL))
        onView(withId(R.id.passwordInput)).perform(typeText(VALID_PASSWORD))

        onView(withId(R.id.login)).perform(click())
        onView(isRoot()).perform(waitFor(500, TimeUnit.MILLISECONDS))

        onView(withId(R.id.feedPlaceholderText)).check(matches(isDisplayed()))
        onView(isRoot()).perform(waitFor(500, TimeUnit.MILLISECONDS))
    }

    @Test
    fun testRegisterUserAndLoginShowsFeed() {
        onView(withId(R.id.register)).perform(click())
        val (email, password) = withRobot(RegisterRobot::class.java).createNewAccount()

        /** Check if we're on Login now */
        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(500, TimeUnit.MILLISECONDS))
        Espresso.onView(ViewMatchers.withId(R.id.login)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        withRobot(LoginInputRobot::class.java).inputCredentials(email, password)
        withRobot(LoginRobot::class.java).performLogin()

        /** We're on the Feed screen now! */
        Espresso.onView(ViewMatchers.withId(R.id.feedPlaceholderText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(500, TimeUnit.MILLISECONDS))
    }
}