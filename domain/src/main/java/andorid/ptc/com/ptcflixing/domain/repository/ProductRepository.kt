package andorid.ptc.com.ptcflixing.domain.repository

import android.ptc.com.ptcflixing.common.Result
import android.ptc.com.ptcflixing.data.model.Product
import android.ptc.com.ptcflixing.data.model.ProductDetails
import androidx.lifecycle.LiveData
import androidx.paging.PagingData

interface ProductRepository {
    suspend fun search(query: String): LiveData<PagingData<Product>>

    suspend fun getProduct(sku: String): Result<ProductDetails>
}
