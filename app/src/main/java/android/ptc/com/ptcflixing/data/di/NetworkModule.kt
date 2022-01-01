package android.ptc.com.ptcflixing.data.di

import android.ptc.com.ptcflixing.data.repository.ProductRepositoryImpl
import android.ptc.com.ptcflixing.data.source.remote.ProductRemoteDataSource
import android.ptc.com.ptcflixing.data.source.remote.ProductService
import android.ptc.com.ptcflixing.domain.repository.ProductRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "http://nd7d1.mocklab.io/"
private const val CONNECT_TIMEOUT = 30L
private const val WRITE_TIMEOUT = 20L
private const val READ_TIMEOUT = 20L

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ProductService = retrofit.create(ProductService::class.java)

    @Singleton
    @Provides
    fun providesRetrofit(converter: Converter.Factory, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converter)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun providesConverter(json: Json): Converter.Factory = json.asConverterFactory("application/json".toMediaType())

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideProductsRepository(productService: ProductService, productRemoteDataSource: ProductRemoteDataSource): ProductRepository =
        ProductRepositoryImpl(productService, productRemoteDataSource)

    @Singleton
    @Provides
    fun providesJson() = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
}
