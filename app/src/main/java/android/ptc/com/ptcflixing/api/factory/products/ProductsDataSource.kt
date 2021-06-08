package android.ptc.com.ptcflixing.api.factory.products

import androidx.paging.PageKeyedDataSource
import android.ptc.com.ptcflixing.api.models.BaseResponse
import android.ptc.com.ptcflixing.api.models.ProductsResponse
import android.ptc.com.ptcflixing.repository.NetworkRepository
import android.ptc.com.ptcflixing.ui.utils.LoadingState
import android.ptc.com.ptcflixing.ui.utils.NetworkState
import android.ptc.com.ptcflixing.ui.utils.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class ProductsDataSource(
    private val networkRepository: NetworkRepository,
    val retryExecutor: Executor,
    private val networkStatusMutableLiveData: SingleLiveEvent<NetworkState>,
    val loadingState: LoadingState,
    val query: String

) : PageKeyedDataSource<Int, ProductsResponse.Result>() {
    private var retry: (() -> Any)? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ProductsResponse.Result>
    ) {
        networkStatusMutableLiveData.postValue(NetworkState.INIT_LOADING)
        try {
            val request = networkRepository.products(query, "1")
            val response = request.execute()
            val items = response.body()?.metadata?.results
            items?.let { callback.onResult(it, null, 2) }
            retry = null
            networkStatusMutableLiveData.postValue(NetworkState.LOADED)
            if (response.isSuccessful)
                loadingState.loadedState()
            else
                loadingState.errorState("Couldn't find a result matches your search")


        } catch (ioException: Exception) {
            loadingState.errorState("Check internet and try again")
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ProductsResponse.Result>
    ) {
        networkStatusMutableLiveData.postValue(NetworkState.LOADING)
        networkRepository.products(
            query,
            params.key.toString()
        ).enqueue(object : Callback<BaseResponse<ProductsResponse>> {

            override fun onResponse(
                call: Call<BaseResponse<ProductsResponse>>,
                response: Response<BaseResponse<ProductsResponse>>
            ) {
                if (response.isSuccessful) {
                    networkStatusMutableLiveData.postValue(NetworkState.LOADED)
                    val items = response.body()?.metadata?.results
                    items?.let { callback.onResult(it, null) }
                    loadingState.loadedState()
                    retry = null
                }
            }

            override fun onFailure(call: Call<BaseResponse<ProductsResponse>>, t: Throwable) {
                loadingState.errorState("Check internet and try again")
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ProductsResponse.Result>
    ) {
    }
}
