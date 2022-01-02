package andorid.ptc.com.ptcflixing.domain.di

import andorid.ptc.com.ptcflixing.domain.repository.ConfigurationRepositoryImpl
import andorid.ptc.com.ptcflixing.domain.repository.ProductRepositoryImpl
import android.ptc.com.ptcflixing.data.remote.source.remote.ConfigurationRemoteDataSource
import android.ptc.com.ptcflixing.data.remote.source.remote.ProductRemoteDataSource
import android.ptc.com.ptcflixing.data.remote.source.remote.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideProductsRepository(productService: ProductService, productRemoteDataSource: ProductRemoteDataSource): andorid.ptc.com.ptcflixing.domain.repository.ProductRepository =
        ProductRepositoryImpl(productService, productRemoteDataSource)

    @Singleton
    @Provides
    fun provideConfigurationRepository(configurationRemoteDataSource: ConfigurationRemoteDataSource): andorid.ptc.com.ptcflixing.domain.repository.ConfigurationRepository =
        ConfigurationRepositoryImpl(configurationRemoteDataSource)

}