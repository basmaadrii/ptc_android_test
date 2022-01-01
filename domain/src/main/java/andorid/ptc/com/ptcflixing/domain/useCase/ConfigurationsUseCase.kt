package andorid.ptc.com.ptcflixing.domain.useCase

import andorid.ptc.com.ptcflixing.domain.repository.ConfigurationRepository
import javax.inject.Inject

class ConfigurationsUseCase @Inject constructor(
    private val configurationRepository: ConfigurationRepository
) {
    suspend fun getConfigurations() = configurationRepository.getConfigurations()
}
