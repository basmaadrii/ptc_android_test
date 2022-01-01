package android.ptc.com.ptcflixing.data.source.remote

import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val productService: ProductService
) : RemoteDataSource() {
    suspend fun getProduct(sku: String) = getResult { productService.getProduct(sku) }
}
