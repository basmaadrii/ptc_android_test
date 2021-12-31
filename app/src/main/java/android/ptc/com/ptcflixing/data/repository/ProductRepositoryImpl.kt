package android.ptc.com.ptcflixing.data.repository

import android.ptc.com.ptcflixing.data.model.Product
import android.ptc.com.ptcflixing.data.source.remote.ProductPagingSource
import android.ptc.com.ptcflixing.data.source.remote.ProductService
import android.ptc.com.ptcflixing.domain.repository.ProductRepository
import androidx.lifecycle.LiveData
import androidx.paging.*
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : ProductRepository {
    override suspend fun search(query: String): LiveData<PagingData<Product>> =
        Pager(PagingConfig(pageSize = 10)) { ProductPagingSource(productService, query) }.liveData

    override suspend fun getProduct(id: Int) {

    }
}