package android.ptc.com.ptcflixing.data.source.remote

import android.ptc.com.ptcflixing.data.model.BaseResponse
import android.ptc.com.ptcflixing.data.model.ProductDetails
import android.ptc.com.ptcflixing.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET("search/{query}/page/{page}/")
    suspend fun search(
        @Path("query") query: String,
        @Path("page") page: Int
    ): Response<BaseResponse<SearchResponse>>

    @GET("product/{sku}/")
    suspend fun getProduct(
        @Path("sku") sku: String
    ): Response<BaseResponse<ProductDetails>>
}