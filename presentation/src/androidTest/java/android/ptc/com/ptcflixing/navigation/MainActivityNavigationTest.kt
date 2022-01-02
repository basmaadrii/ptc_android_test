package android.ptc.com.ptcflixing.navigation

import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.ui.main.DEFAULT_QUERY
import android.ptc.com.ptcflixing.ui.main.QUERY_KEY
import android.ptc.com.ptcflixing.ui.result.ResultFragment
import android.ptc.com.ptcflixing.utils.launchFragmentInHiltContainer
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityNavigationTest {

    @Test
    fun testNavigationToResultScreen_CorrectQuery() {
        runBlocking {
            val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            val bundle = bundleOf(Pair(QUERY_KEY, DEFAULT_QUERY))
            launchFragmentInHiltContainer<ResultFragment>(fragmentArgs = bundle) {
                navController.setGraph(R.navigation.nav_graph)
                Navigation.setViewNavController(requireView(), navController)
            }
            onView(withText(DEFAULT_QUERY)).check(matches(isDisplayed()))
            assertEquals(navController.currentDestination?.id, R.id.resultFragment)
        }
    }
}
