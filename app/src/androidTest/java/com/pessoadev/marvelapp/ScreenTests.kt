package com.pessoadev.marvelapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.pessoadev.marvelapp.presentation.characters.CharactersActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ScreenTests : BaseTest() {

    @get:Rule
    val activityRule = ActivityTestRule(CharactersActivity::class.java)

    @Test
    fun checkToolbarTitle() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.toolbar))))
    }

    @Test
    fun checkFavoriteFlow() {
        suspendUntilSuccess({
            onView(withId(R.id.charactersRecyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    clickOnViewChild(R.id.cardView)
                )
            )
            onView(withId(R.id.action_favorite)).perform(click())
            onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
            onView(withText("FAVORITES")).perform(click())
            onView(withId(R.id.favoriteRecyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    clickOnViewChild(R.id.cardView)
                )
            )
            onView(withId(R.id.action_favorite)).perform(click())
            onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        })

    }


}