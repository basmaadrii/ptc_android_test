package android.ptc.com.ptcflixing.utils

import android.ptc.com.ptcflixing.data.remote.source.remote.ProductService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import retrofit2.Retrofit
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

@ExperimentalSerializationApi
abstract class BaseRemoteDataSourceTest {
    protected val mockWebServer = MockWebServer().also { it.start() }

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    protected val service: ProductService = Retrofit.Builder()
        .baseUrl(mockWebServer.url(""))
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(ProductService::class.java)

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }
}

fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
    val source = inputStream?.let { inputStream.source().buffer() } ?: return
    val body = source.readString(StandardCharsets.UTF_8)
    enqueue(MockResponse().setResponseCode(code).setBody(body))
}