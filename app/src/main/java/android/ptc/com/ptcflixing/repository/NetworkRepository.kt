package android.ptc.com.ptcflixing.repository

import android.ptc.com.ptcflixing.api.models.BaseResponse
import android.ptc.com.ptcflixing.api.models.ConfigurationsResponse
import android.ptc.com.ptcflixing.api.models.ProductDetailsResponse
import android.ptc.com.ptcflixing.api.models.ProductsResponse
import android.ptc.com.ptcflixing.api.service.ClientService
import io.reactivex.Observable
import retrofit2.Call

class NetworkRepository() {
    var clientService = ClientService.getClient()

    fun getConfigurations(): Observable<ConfigurationsResponse> {
        return clientService.configurations()
    }

    fun products(
        query: String,
        pageNumber: String
    ): Call<BaseResponse<ProductsResponse>> {
        return clientService.products(
            query,
            pageNumber
        )
    }

    fun getProductDetails(
        productId: String?
    ): Observable<BaseResponse<ProductDetailsResponse>> {
        return clientService.productDetails(
            productId
        )
    }


}