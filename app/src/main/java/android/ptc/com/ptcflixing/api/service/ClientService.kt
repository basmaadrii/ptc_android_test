package android.ptc.com.ptcflixing.api.service

import android.ptc.com.ptcflixing.api.LiveDataCallAdapterFactory
import android.ptc.com.ptcflixing.api.models.BaseResponse
import android.ptc.com.ptcflixing.api.models.ConfigurationsResponse
import android.ptc.com.ptcflixing.api.models.ProductDetailsResponse
import android.ptc.com.ptcflixing.api.models.ProductsResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientService {

    companion object {
        fun getClient(): ClientService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://nd7d1.mocklab.io/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(HttpClient.getClient())
                .build()

            return retrofit.create(ClientService::class.java)
        }

    }

    @GET("configurations/")
    fun configurations(): Observable<ConfigurationsResponse>

    @GET("search/{query}/page/{pageNumber}/")
    fun products(
        @Path("query") query: String,
        @Path("pageNumber") page: String
    ): Call<BaseResponse<ProductsResponse>>

    @GET("product/{id}/")
    fun productDetails(
        @Path("id") productId: String?
    ): Observable<BaseResponse<ProductDetailsResponse>>

}