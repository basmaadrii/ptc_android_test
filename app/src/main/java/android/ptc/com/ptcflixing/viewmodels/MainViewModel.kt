package android.ptc.com.ptcflixing.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.ptc.com.ptcflixing.R
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import android.ptc.com.ptcflixing.api.factory.products.ProductsDataSourceFactory
import android.ptc.com.ptcflixing.api.models.BaseResponse
import android.ptc.com.ptcflixing.api.models.ProductDetailsResponse
import android.ptc.com.ptcflixing.api.models.ProductsResponse
import android.ptc.com.ptcflixing.repository.NetworkRepository
import android.ptc.com.ptcflixing.repository.SharedPreferenceRepository
import android.ptc.com.ptcflixing.ui.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class MainViewModel(application: Application) :
    AndroidViewModel(application) {
    var backCLickEvent = SingleLiveEvent<Void>()
    var networkRepository = NetworkRepository()
    var preferenceRepository = SharedPreferenceRepository(application)
    var appExecutors = AppExecutors()
    var networkStatusMutableLiveData = SingleLiveEvent<NetworkState>()
    var toolbarData = ToolbarData()

    fun backClick() {
        backCLickEvent.call()
    }

    fun getCurrency(): String? {
        return preferenceRepository.getConfigurations()?.metadata?.currency?.currencySymbol
    }

    // Products
    var productsLoadingState: LoadingState = LoadingState()
    var queryMutable = MutableLiveData<String>()
    var productsLiveData =
        Transformations.switchMap<String, PagedList<ProductsResponse.Result>>(
            queryMutable
        ) {
            if (!it.isNullOrBlank()) {
                val dataSourceFactory = ProductsDataSourceFactory(
                    networkRepository,
                    appExecutors.networkIO(),
                    networkStatusMutableLiveData,
                    productsLoadingState,
                    it
                )
                val config = PagedList.Config.Builder()
                    .setPageSize(5)
                    .build()
                LivePagedListBuilder(dataSourceFactory, config)
                    .setInitialLoadKey(null)
                    .setBoundaryCallback(null)
                    .setFetchExecutor(appExecutors.networkIO())
                    .build()
            } else {
                AbsentLiveData.create()
            }
        }

    fun retryProducts(query: String) {
        productsLoadingState.setRetry { retryProducts(query) }
        productsLoadingState.loadingState()
        queryMutable.value = query
    }


    // Product Details
    var productDetailsLoadingState: LoadingState = LoadingState()
    val productDetailsMutableLiveData = MutableLiveData<BaseResponse<ProductDetailsResponse>>()
    fun getProductDetails(
        context: Context,
        productId: String?,
        onFinish: (Boolean, String?) -> Unit
    ) {
        productDetailsLoadingState.setRetry { getProductDetails(context, productId, onFinish) }
        productDetailsLoadingState.loadingState()
        networkRepository.getProductDetails(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())?.subscribe(
                { it ->
                    when (it.success) {
                        true -> {
                            onFinish.invoke(true, null)
                            productDetailsMutableLiveData.value = it
                        }
                        else -> {
                            onFinish.invoke(false, it.messages?.error?.message)
                        }
                    }

                },
                {
                    if (InternetCheckUtils.getConnectionType(context) == 0) {
                        onFinish.invoke(
                            false,
                            context.resources.getString(R.string.check_connection_try_again)
                        )
                    } else {
                        onFinish.invoke(false, it.message)

                    }
                }
            )
    }

}