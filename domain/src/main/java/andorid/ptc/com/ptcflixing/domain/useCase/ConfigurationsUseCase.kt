package andorid.ptc.com.ptcflixing.domain.useCase

import andorid.ptc.com.ptcflixing.domain.repository.ConfigurationRepository
import android.ptc.com.ptcflixing.common.Result
import android.ptc.com.ptcflixing.data.utils.SharedPreferenceManager
import javax.inject.Inject

class ConfigurationsUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository
) {
    suspend fun getConfigurations() = configurationRepository.getConfigurations().also { result ->
        if (result.isSuccessful()) SharedPreferenceManager.currency = (result as Result.Success).data.currency
    }
}
