package android.ptc.com.ptcflixing.ui.product

import andorid.ptc.com.ptcflixing.domain.useCase.GetProductDetailsUseCase
import android.app.Application
import android.ptc.com.ptcflixing.common.Result
import android.ptc.com.ptcflixing.data.model.Image
import android.ptc.com.ptcflixing.data.model.ProductDetails
import android.ptc.com.ptcflixing.utils.getErrorEvent
import android.ptc.com.ptcflixing.utils.getLoadingEvent
import android.ptc.com.ptcflixing.utils.getSuccessEvent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    application: Application,
    private val getProductDetailsUseCase: GetProductDetailsUseCase
) : AndroidViewModel(application) {
    private val productDetailsLiveData = MutableLiveData<Result<ProductDetails>>()
    val productDetailsSuccessEvent = productDetailsLiveData.getSuccessEvent()
    val productDetailsErrorEvent = productDetailsLiveData.getErrorEvent()
    val productDetailsLoadingEvent = productDetailsLiveData.getLoadingEvent()
    val imagesLiveData = MutableLiveData<List<Image>>()

    fun getProduct(sku: String?) {
        sku?.let {
            viewModelScope.launch {
                productDetailsLiveData.value = Result.Loading
                val productDetailsResult = getProductDetailsUseCase.getProductDetails(sku)
                productDetailsLiveData.value = productDetailsResult
                if (productDetailsResult !is Result.Success) return@launch
                imagesLiveData.value = productDetailsResult.data.images.map { Image(it) }.apply {
                    first().isSelected = true
                }
            }
        }
    }

    fun changeImageSelected(position: Int) {
        val images = imagesLiveData.value ?: return
        val selectedPosition = images.indexOfFirst { it.isSelected }
        images[selectedPosition].isSelected = false
        images[position].isSelected = true
        imagesLiveData.value = images
    }
}