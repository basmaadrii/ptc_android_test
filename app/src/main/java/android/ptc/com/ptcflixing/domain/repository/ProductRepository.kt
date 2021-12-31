package android.ptc.com.ptcflixing.domain.repository

import android.ptc.com.ptcflixing.data.model.Product
import androidx.lifecycle.LiveData
import androidx.paging.PagingData

interface ProductRepository {
    suspend fun search(query: String): LiveData<PagingData<Product>>

    suspend fun getProduct(id: Int)
}
