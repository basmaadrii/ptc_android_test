package android.ptc.com.ptcflixing.ui.search

import andorid.ptc.com.ptcflixing.domain.useCase.ConfigurationsUseCase
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    application: Application,
    private val configurationsUseCase: ConfigurationsUseCase
) : AndroidViewModel(application) {

    fun setupConfiguration() = viewModelScope.launch {
        configurationsUseCase.getConfigurations()
    }

}
