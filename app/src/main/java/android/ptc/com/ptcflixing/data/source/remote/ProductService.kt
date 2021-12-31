package android.ptc.com.ptcflixing.data.source.remote

import android.ptc.com.ptcflixing.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET("search/{query}/page/{page}/")
    suspend fun search(
        @Path("query") query: String,
        @Path("page") page: Int
    ) : Response<SearchResponse>

    @GET("product/{id}/")
    suspend fun getProduct(@Path("id") productId: String)
}