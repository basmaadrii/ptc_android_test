package android.ptc.com.ptcflixing.api.factory.products

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import android.ptc.com.ptcflixing.api.models.ProductsResponse
import android.ptc.com.ptcflixing.repository.NetworkRepository
import android.ptc.com.ptcflixing.ui.utils.LoadingState
import android.ptc.com.ptcflixing.ui.utils.NetworkState
import android.ptc.com.ptcflixing.ui.utils.SingleLiveEvent
import java.util.concurrent.Executor

class ProductsDataSourceFactory(
    private val networkRepository: NetworkRepository,
    val retryExecutor: Executor,
    private val networkStatusMutableLiveData: SingleLiveEvent<NetworkState>,
    val loadingState: LoadingState,
    val query: String
) : DataSource.Factory<Int, ProductsResponse.Result>() {

    val sourceLiveData = MutableLiveData<ProductsDataSource>()
    override fun create(): DataSource<Int, ProductsResponse.Result> {
        val source = ProductsDataSource(
            networkRepository,
            retryExecutor,
            networkStatusMutableLiveData,
            loadingState,
            query
        )
        sourceLiveData.postValue(source)
        return source
    }
}