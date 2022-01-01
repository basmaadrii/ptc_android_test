package andorid.ptc.com.ptcflixing.domain.repository

import android.ptc.com.ptcflixing.data.model.Product
import android.ptc.com.ptcflixing.data.source.remote.ProductPagingSource
import android.ptc.com.ptcflixing.data.source.remote.ProductRemoteDataSource
import android.ptc.com.ptcflixing.data.source.remote.ProductService
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productRemoteDataSource: ProductRemoteDataSource
) : ProductRepository {
    override suspend fun search(query: String): LiveData<PagingData<Product>> =
        Pager(PagingConfig(pageSize = 10)) { ProductPagingSource(productService, query) }.liveData

    override suspend fun getProduct(sku: String) = productRemoteDataSource.getProduct(sku)
}