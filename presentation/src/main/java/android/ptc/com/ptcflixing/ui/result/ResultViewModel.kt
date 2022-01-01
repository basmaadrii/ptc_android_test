package android.ptc.com.ptcflixing.ui.result

import andorid.ptc.com.ptcflixing.domain.useCase.ConfigurationsUseCase
import andorid.ptc.com.ptcflixing.domain.useCase.SearchProductUseCase
import android.app.Application
import android.ptc.com.ptcflixing.common.Result
import android.ptc.com.ptcflixing.data.model.Configuration
import android.ptc.com.ptcflixing.data.model.Product
import android.ptc.com.ptcflixing.utils.SharedPreferenceManager
import android.ptc.com.ptcflixing.utils.getErrorEvent
import android.ptc.com.ptcflixing.utils.getLoadingEvent
import android.ptc.com.ptcflixing.utils.getSuccessEvent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    application: Application,
    private val searchProductUseCase: SearchProductUseCase,
    private val configurationsUseCase: ConfigurationsUseCase
) : AndroidViewModel(application) {
    lateinit var productLiveData: LiveData<PagingData<Product>>
    private val configurationLiveData = MutableLiveData<Result<Configuration>>()
    val configurationSuccessEvent = configurationLiveData.getSuccessEvent()
    val configurationErrorEvent = configurationLiveData.getErrorEvent()
    val configurationLoadingEvent = configurationLiveData.getLoadingEvent()

    fun search(query: String) = viewModelScope.launch {
        configurationLiveData.value = Result.Loading
        val configuration = configurationsUseCase.getConfigurations()
        configurationLiveData.value = configuration
        if (configuration is Result.Error) return@launch
        SharedPreferenceManager.currency = (configuration as Result.Success).data.currency
        productLiveData = searchProductUseCase.search(query).cachedIn(viewModelScope)
    }
}
