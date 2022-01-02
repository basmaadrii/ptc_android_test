package android.ptc.com.ptcflixing.data.remote.source.remote

import android.ptc.com.ptcflixing.data.model.Product
import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

class ProductPagingSource @Inject constructor(
    private val productService: ProductService,
    val query: String
) : PagingSource<Int, Product>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = productService.search(query, nextPageNumber)
            if (response.isSuccessful) {
                response.body()?.metadata?.let { return LoadResult.Page(data = it.results, prevKey = null, nextKey = nextPageNumber + 1) }
                response.body()?.messages?.let { return LoadResult.Error(Throwable(it.error.message)) }
            }
            return LoadResult.Error(Throwable(response.message()))
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>) =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}
