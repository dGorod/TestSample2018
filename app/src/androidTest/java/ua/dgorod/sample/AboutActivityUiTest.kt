package ua.dgorod.sample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ua.dgorod.sample.ui.activity.AboutActivity

/**
 * Created by dgorodnytskyi on 10/11/18.
 */
@RunWith(AndroidJUnit4::class)
class AboutActivityUiTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(AboutActivity::class.java)

    @Test
    fun checkAboutText() {
        onView(withId(R.id.aboutText)).check(matches(allOf(isDisplayed(), withText(R.string.about_app))))
    }
}