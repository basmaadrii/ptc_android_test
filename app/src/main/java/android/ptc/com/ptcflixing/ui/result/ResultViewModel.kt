package android.ptc.com.ptcflixing.ui.result

import android.app.Application
import android.ptc.com.ptcflixing.data.model.Product
import android.ptc.com.ptcflixing.domain.useCase.SearchProductUseCase
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    application: Application,
    private val searchProductUseCase: SearchProductUseCase
) : AndroidViewModel(application) {
    lateinit var productLiveData: LiveData<PagingData<Product>>

    fun search(query: String) = viewModelScope.launch {
        productLiveData = searchProductUseCase.search(query)
    }
}
