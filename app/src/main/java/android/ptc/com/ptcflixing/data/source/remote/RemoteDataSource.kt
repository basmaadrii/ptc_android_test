package android.ptc.com.ptcflixing.data.source.remote

import android.ptc.com.ptcflixing.common.Result
import retrofit2.Response
import java.lang.Exception

abstract class RemoteDataSource {
    protected suspend fun <T> getResult(execute: suspend () -> Response<T>): Result<T> {
        try {
            val response = execute()
            if (response.isSuccessful) {
                response.body()?.let { return Result.Success(it) }
            }
            return Result.Error("Error code: ${response.code()}.\n${response.message()}")
        } catch (e: Exception) {
            return Result.Error(e.toString())
        }
    }
}
