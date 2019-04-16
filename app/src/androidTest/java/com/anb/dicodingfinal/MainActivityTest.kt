package com.anb.dicodingfinal

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import com.anb.dicodingfinal.feature.home.HomeActivity
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)


    @Test
    fun testAppBehaviour(){

        // Digunakan Thread.sleep untuk menghindari program error karena data belum terambil

        Thread.sleep(5000)
        onView(ViewMatchers.withId(R.id.spinnerLeague))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.spinnerLeague)).perform(ViewActions.click())
        Thread.sleep(500)
        onView(ViewMatchers.withText("English Premier League"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("English Premier League")).perform(ViewActions.click())
        Thread.sleep(300)
        onView(AllOf.allOf(ViewMatchers.withId(R.id.menu_search), ViewMatchers.isDisplayed())).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(AllOf.allOf(ViewMatchers.withId(R.id.menu_search), ViewMatchers.isDisplayed())).perform(ViewActions.click())
        onView(ViewMatchers.isAssignableFrom(EditText::class.java)).perform(ViewActions.typeText("cester"), ViewActions.pressImeActionButton())
        Thread.sleep(300)
        onView(ViewMatchers.withText("NEXT MATCH")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("NEXT MATCH")).perform(ViewActions.click())
        Thread.sleep(300)
        onView(AllOf.allOf(ViewMatchers.withId(R.id.rv_list_match), ViewMatchers.isDisplayed())).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(AllOf.allOf(ViewMatchers.withId(R.id.rv_list_match), ViewMatchers.isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(500)
        onView(ViewMatchers.withId(R.id.add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        Thread.sleep(200)
        onView(ViewMatchers.withText("Added to favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(500)
        pressBack()
        onView(ViewMatchers.isAssignableFrom(EditText::class.java)).perform(ViewActions.clearText(), ViewActions.pressImeActionButton())
        Thread.sleep(300)
        onView(ViewMatchers.withText("Team")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("Team")).perform(ViewActions.click())
        Thread.sleep(1000)
        onView(AllOf.allOf(ViewMatchers.withId(R.id.menu_search), ViewMatchers.isDisplayed())).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(AllOf.allOf(ViewMatchers.withId(R.id.menu_search), ViewMatchers.isDisplayed())).perform(ViewActions.click())
        onView(ViewMatchers.isAssignableFrom(EditText::class.java)).perform(ViewActions.typeText("arse"), ViewActions.pressImeActionButton())
        Thread.sleep(300)
        onView(AllOf.allOf(ViewMatchers.withText("Arsenal"), ViewMatchers.isDisplayed())).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(AllOf.allOf(ViewMatchers.withText("Arsenal"), ViewMatchers.isDisplayed())).perform(ViewActions.click())
        Thread.sleep(500)
        onView(ViewMatchers.withId(R.id.add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        Thread.sleep(200)
        onView(ViewMatchers.withText("Added to favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)
        onView(ViewMatchers.withText("Nacho Monreal")).perform(ViewActions.scrollTo())
        onView(ViewMatchers.withText("Danny Welbeck")).perform(ViewActions.click())
        Thread.sleep(300)
        pressBack()
        pressBack()
        onView(ViewMatchers.withText("Favorite")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("Favorite")).perform(ViewActions.click())
        Thread.sleep(300)
        onView(AllOf.allOf(ViewMatchers.withText("Leicester"), ViewMatchers.isDisplayed())).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(AllOf.allOf(ViewMatchers.withText("Leicester"), ViewMatchers.isDisplayed())).perform(ViewActions.click())
        Thread.sleep(500)
        onView(ViewMatchers.withId(R.id.add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        Thread.sleep(200)
        onView(ViewMatchers.withText("Removed from favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(200)
        pressBack()
        onView(AllOf.allOf(ViewMatchers.withId(R.id.srl_list_match), ViewMatchers.isDisplayed())).perform(ViewActions.swipeDown())
        onView(ViewMatchers.withText("TEAM")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("TEAM")).perform(ViewActions.click())
        Thread.sleep(300)
        onView(AllOf.allOf(ViewMatchers.withId(R.id.srl_list_team), ViewMatchers.isDisplayed())).perform(ViewActions.swipeDown())
        Thread.sleep(500)
        onView(AllOf.allOf(ViewMatchers.withText("Arsenal"), ViewMatchers.isDisplayed())).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(AllOf.allOf(ViewMatchers.withText("Arsenal"), ViewMatchers.isDisplayed())).perform(ViewActions.click())
        Thread.sleep(500)
        onView(ViewMatchers.withId(R.id.add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        Thread.sleep(200)
        onView(ViewMatchers.withText("Removed from favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(200)
        pressBack()
        onView(AllOf.allOf(ViewMatchers.withId(R.id.srl_list_team), ViewMatchers.isDisplayed())).perform(ViewActions.swipeDown())
        Thread.sleep(3000)
    }


}