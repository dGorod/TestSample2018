package ua.dgorod.sample

import android.os.Build
import io.reactivex.Flowable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.declare
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import ua.dgorod.sample.domain.interactor.RepoInteractor
import ua.dgorod.sample.ui.activity.AboutActivity
import ua.dgorod.sample.ui.activity.MainActivity

/**
 * Created by dgorodnytskyi on 10/11/18.
 */
@RunWith(RobolectricTestRunner::class)
@Config(minSdk = Build.VERSION_CODES.LOLLIPOP, application = TestApp::class)
class AboutActivityStartTest : KoinTest {

    @Before
    fun before() {
        val mockedInteractor = mock(RepoInteractor::class.java)
        `when`(mockedInteractor.getAll(1)).thenReturn(Flowable.just(emptyList()))

        declare { single { mockedInteractor } }
    }

    @Test
    fun checkAboutActivityStart() {
        val mainActivity = Robolectric.setupActivity(MainActivity::class.java)
        val mainShadowed = Shadows.shadowOf(mainActivity)

        mainShadowed.clickMenuItem(R.id.menu_about)

        val aboutActivityIntent = mainShadowed.nextStartedActivity
        assertNotNull(aboutActivityIntent)

        val intentShadowed = Shadows.shadowOf(aboutActivityIntent)

        assertEquals(intentShadowed.intentClass.simpleName, AboutActivity::class.java.simpleName)
    }
}
