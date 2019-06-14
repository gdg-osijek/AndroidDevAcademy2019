package com.example.koinexample.utils

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher
import java.util.concurrent.TimeUnit

/**
 * Perform action of waiting for a specific time.
 */
fun waitFor(duration: Long, unit: TimeUnit): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isRoot()
        }

        override fun getDescription(): String {
            return "Wait for ${unit.toMillis(duration)} milliseconds."
        }

        override fun perform(uiController: UiController, view: View) {
            uiController.loopMainThreadForAtLeast(unit.toMillis(duration))
        }
    }
}


const val VALID_EMAIL = "test@test.com"
const val VALID_PASSWORD = ".Password2"

const val INVALID_PASSWORD = ".Password"