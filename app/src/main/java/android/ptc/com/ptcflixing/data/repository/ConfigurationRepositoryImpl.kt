package android.ptc.com.ptcflixing.data.repository

import android.ptc.com.ptcflixing.data.source.remote.ConfigurationRemoteDataSource
import android.ptc.com.ptcflixing.domain.repository.ConfigurationRepository
import javax.inject.Inject

class ConfigurationRepositoryImpl @Inject constructor(
    private val configurationRemoteDataSource: ConfigurationRemoteDataSource
) : ConfigurationRepository {
    override suspend fun getConfigurations() = configurationRemoteDataSource.getConfigurations()
}
