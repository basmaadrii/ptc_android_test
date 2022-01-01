package android.ptc.com.ptcflixing.domain.useCase

import android.ptc.com.ptcflixing.domain.repository.ConfigurationRepository
import javax.inject.Inject

class ConfigurationsUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository
) {
    suspend fun getConfigurations() = configurationRepository.getConfigurations()
}
