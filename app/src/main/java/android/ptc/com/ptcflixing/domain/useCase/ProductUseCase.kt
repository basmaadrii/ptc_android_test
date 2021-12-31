package android.ptc.com.ptcflixing.domain.useCase

import android.ptc.com.ptcflixing.domain.repository.ProductRepository
import javax.inject.Inject

class SearchProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend fun search(query: String) = productRepository.search(query)
}
