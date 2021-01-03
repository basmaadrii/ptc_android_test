package android.ptc.com.ptcflixing.viewModels

import android.ptc.com.ptcflixing.models.*
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class ProductListViewModel : ViewModel() {
    val searchQuery = MutableLiveData<String>()
    private val product =  MutableLiveData<MetadataProductDetail>()
    private val productsListing = MutableLiveData<List<Result>>()
    private val error = MutableLiveData<String>()
    fun getError(): LiveData<String> {
        return error
    }
    fun getProducts(): LiveData<List<Result>> {
        return productsListing
    }
    fun getProduct(): LiveData<MetadataProductDetail> {
        return product
    }
    fun loadProducts(search: String, page : Int = 1) {
            getProductListing(search,page)
    }
    fun loadProductDetail(sku: String) {
        CoroutineScope(Dispatchers.IO).launch {
            getProductItem(sku)
        }
    }
    private fun getProductListing(searchQuery: String, page : Int) {
        val httpUrl = HttpUrl.Builder()
                .scheme("https")
                .host("nd7d1.mocklab.io")
                .addPathSegment("search")
                .addPathSegment(searchQuery)
                .addPathSegment("page")
                .addPathSegment(page.toString())
                .addPathSegment("")
                .build()
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(httpUrl)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                call.cancel()
            }
            @Throws(IOException::class)
            override fun onResponse(
                    call: Call,
                    response: Response
            ) {
                val myResponse = response.body!!.string()
                val item = ProductListing.fromJson(myResponse)
                productsListing.postValue(item?.metadata?.results)
            }
        })
    }

    private suspend fun getProductItem(sku: String){
        val httpUrl = HttpUrl.Builder()
                .scheme("https")
                .host("nd7d1.mocklab.io")
                .addPathSegment("product")
                .addPathSegment(sku)
                .addPathSegment("")
                .build()
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(httpUrl)
                .build()
        val result = client.newCall(request).execute()
         val responseValue = result.body!!.string()
         if(result.isSuccessful){
             val jsonObj = JSONObject(responseValue)
             val success = jsonObj.getBoolean("success")
             if(success){
                 val item = ProductDetail.fromJson(responseValue)
                 product.postValue(item?.metadata)
             }
             else{
                 error.postValue(android.ptc.com.ptcflixing.models.Response.fromJson(responseValue)?.messages?.error?.message)
             }
         } else{
             error.postValue(responseValue)
         }
    }
}