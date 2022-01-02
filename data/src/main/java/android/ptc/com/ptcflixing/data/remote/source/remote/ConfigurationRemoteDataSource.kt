package android.ptc.com.ptcflixing.data.remote.source.remote

import javax.inject.Inject

class ConfigurationRemoteDataSource @Inject constructor(
    private val productService: ProductService
) : RemoteDataSource() {
    suspend fun getConfigurations() = getResult { productService.getConfigurations() }
}
