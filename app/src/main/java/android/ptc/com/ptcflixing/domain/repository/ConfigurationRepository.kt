package android.ptc.com.ptcflixing.domain.repository

import android.ptc.com.ptcflixing.common.Result
import android.ptc.com.ptcflixing.data.model.Configuration

interface ConfigurationRepository {
    suspend fun getConfigurations(): Result<Configuration>
}
