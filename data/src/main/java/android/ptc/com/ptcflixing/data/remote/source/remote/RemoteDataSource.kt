package android.ptc.com.ptcflixing.data.remote.source.remote

import android.ptc.com.ptcflixing.common.Result
import android.ptc.com.ptcflixing.data.model.BaseResponse
import retrofit2.Response
import java.lang.Exception

abstract class RemoteDataSource {
    protected suspend fun <T> getResult(execute: suspend () -> Response<BaseResponse<T>>): Result<T> {
        try {
            val response = execute()
            if (response.isSuccessful) {
                response.body()?.metadata?.let { return Result.Success(it) }
                response.body()?.messages?.let { return Result.Error(it.error.message) }
            }
            return Result.Error("Error code: ${response.code()}.\n${response.message()}")
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(e.toString())
        }
    }
}
