package android.ptc.com.ptcflixing.data.remote.source

import android.ptc.com.ptcflixing.data.model.Product
import android.ptc.com.ptcflixing.data.remote.source.remote.ProductPagingSource
import android.ptc.com.ptcflixing.utils.BaseRemoteDataSourceTest
import android.ptc.com.ptcflixing.utils.enqueueResponse
import androidx.paging.PagingSource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.Test

private const val QUERY = "phone"
private const val LOAD_SIZE = 2

@ExperimentalSerializationApi
class ProductPagingSourceTest : BaseRemoteDataSourceTest() {
    private val pagingSource = ProductPagingSource(service, QUERY)

    private val expectedValue = listOf(
        Product(
            sku = "1",
            name = "Samsung Galaxy S9",
            brand = "Samsung",
            maxSavingPercentage = 30,
            price = 53996F,
            specialPrice = 37990F,
            image = "https://cdn2.gsmarena.com/vv/bigpic/samsung-galaxy-s9-.jpg",
            ratingAverage = 5F
        ),
        Product(
            sku = "2",
            name = "Huawei P20 Pro",
            brand = "Huawei",
            maxSavingPercentage = 45,
            price = 43899F,
            specialPrice = 23990F,
            image = "https://cdn2.gsmarena.com/vv/bigpic/huawei-p20-pro-.jpg",
            ratingAverage = 4F
        )
    )

    @Test
    fun searchProducts_CorrectResponse() {
        runBlocking {
            mockWebServer.enqueueResponse("searchResponseCorrect.json", 200)
            val actualResult = searchProducts()
            val expectedResult = PagingSource.LoadResult.Page(expectedValue, null, 2)
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun searchProducts_MissingFieldResponse() {
        runBlocking {
            mockWebServer.enqueueResponse("searchResponseMissingField.json", 200)
            val actualResult = searchProducts()
            assert(actualResult is PagingSource.LoadResult.Error)
        }
    }

    private suspend fun searchProducts() = pagingSource.load(
        PagingSource.LoadParams.Refresh(
            key = 1,
            loadSize = LOAD_SIZE,
            placeholdersEnabled = false
        )
    )
}