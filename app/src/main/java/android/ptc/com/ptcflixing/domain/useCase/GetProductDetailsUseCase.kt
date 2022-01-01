package android.ptc.com.ptcflixing.domain.useCase

import android.ptc.com.ptcflixing.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend fun getProductDetails(sku: String) = productRepository.getProduct(sku)
}