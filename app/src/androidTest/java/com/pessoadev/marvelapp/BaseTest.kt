package com.pessoadev.marvelapp

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.test.espresso.matcher.BoundedMatcher
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.Drawable






open class BaseTest {

    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) =
            ViewActions.click().perform(uiController, view.findViewById<View>(viewId))
    }

    fun suspendUntilSuccess(actionToSucceed: () -> Unit, iteration: Int = 0) {
        try {
            actionToSucceed.invoke()
        } catch (e: Throwable) {
            Thread.sleep(200)
            val incrementedIteration: Int = iteration + 1
            if (incrementedIteration == 50) {
                Assert.fail("Failed after waiting for action to succeed for 10 seconds.")
            }
            suspendUntilSuccess(actionToSucceed, incrementedIteration)
        }
    }

    fun withDrawable(@DrawableRes id: Int) = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("ImageView with drawable same as drawable with id $id")
        }

        override fun matchesSafely(view: View): Boolean {
            val context = view.context
            val expectedBitmap = context.getDrawable(id)?.toBitmap()

            return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
        }
    }

    fun withActionIconDrawable(@DrawableRes resourceId: Int): Matcher<View> {
        return object : BoundedMatcher<View, ActionMenuItemView>(ActionMenuItemView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has image drawable resource $resourceId")
            }

            public override fun matchesSafely(actionMenuItemView: ActionMenuItemView): Boolean {
                return sameBitmap(
                    actionMenuItemView.context,
                    actionMenuItemView.itemData.icon,
                    resourceId
                )
            }
        }
    }

    private fun sameBitmap(context: Context, drawable: Drawable?, resourceId: Int): Boolean {
        var drawable = drawable
        var otherDrawable = context.getResources().getDrawable(resourceId)
        if (drawable == null || otherDrawable == null) {
            return false
        }
        if (drawable is StateListDrawable && otherDrawable is StateListDrawable) {
            drawable = drawable.current
            otherDrawable = otherDrawable!!.getCurrent()
        }
        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap
            val otherBitmap = (otherDrawable as BitmapDrawable).bitmap
            return bitmap.sameAs(otherBitmap)
        }
        return false
    }
}