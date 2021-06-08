package android.ptc.com.ptcflixing.viewmodels

import android.app.Activity
import android.os.Build
import android.ptc.com.ptcflixing.models.ProductDetailsResponseTest
import android.ptc.com.ptcflixing.ui.activities.MainActivity
import androidx.lifecycle.LifecycleOwner
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    lateinit var mainViewModel: MainViewModel
    val activity: Activity = Robolectric.setupActivity(MainActivity::class.java)

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(activity.application)
    }

    @Test
    fun getProductDetails() {
        mainViewModel.getProductDetails(activity.baseContext, "1") { succes, error ->
            assertThat(mainViewModel.productDetailsMutableLiveData.value).isEqualTo(
                ProductDetailsResponseTest.response
            )
        }
    }

    @Test
    fun getProducts() {
        mainViewModel.retryProducts("phone")
        mainViewModel.productsLiveData.observe(activity as LifecycleOwner, {
            if (it.isNotEmpty()) {
                assertThat(it.size).isEqualTo(20)

            }
        })
    }
}