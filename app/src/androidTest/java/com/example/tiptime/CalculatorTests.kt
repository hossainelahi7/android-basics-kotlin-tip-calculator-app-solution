package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.containsString

@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun calculate_20_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))
        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("10.00"))))
    }

    @Test
    fun calculate_15_percent_tip_cancel_roundup() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))
        onView(withId(R.id.option_fifteen_percent)).perform(click())
        onView(withId(R.id.round_up_switch)).check(matches(isChecked())).perform(scrollTo(), click())
        onView(withId(R.id.calculate_button)).perform(scrollTo(), click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("7.5"))))
    }

    @Test
    fun calculate_18_percent_tip_cancel_roundup(){
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("40.00"))
        onView(withId(R.id.option_eighteen_percent)).perform(click())
        onView(withId(R.id.round_up_switch)).check(matches(isChecked())).perform(scrollTo(), click())
        onView(withId(R.id.calculate_button)).perform(scrollTo(), click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("7.2"))))

    }

}