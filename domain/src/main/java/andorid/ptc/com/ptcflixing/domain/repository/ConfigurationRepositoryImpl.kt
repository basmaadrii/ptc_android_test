package andorid.ptc.com.ptcflixing.domain.repository

import android.ptc.com.ptcflixing.data.source.remote.ConfigurationRemoteDataSource
import javax.inject.Inject

class ConfigurationRepositoryImpl @Inject constructor(
    private val configurationRemoteDataSource: ConfigurationRemoteDataSource
) : ConfigurationRepository {
    override suspend fun getConfigurations() = configurationRemoteDataSource.getConfigurations()
}
