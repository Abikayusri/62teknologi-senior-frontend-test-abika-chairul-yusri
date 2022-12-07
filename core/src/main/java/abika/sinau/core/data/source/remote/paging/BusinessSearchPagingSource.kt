package abika.sinau.core.data.source.remote.paging

import abika.sinau.core.data.source.remote.network.ApiService
import abika.sinau.core.data.source.remote.response.BusinessSearchResponse
import androidx.paging.PagingSource
import androidx.paging.PagingState


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */

//class BusinessSearchPagingSource (
//    private val apiService: ApiService
//) : PagingSource<Int, BusinessSearchResponse.Businesse>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BusinessSearchResponse.Businesse> {
//        return try {
//            val position = params.key ?: INITIAL_PAGE_INDEX
//            val responseData = apiService.getBusinessSearchPaging(position, params.loadSize)
//            LoadResult.Page(
//                data = responseData,
//                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
//                nextKey = if (responseData.isNullOrEmpty()) null else position + 1
//            )
//        } catch (exception: Exception) {
//            return LoadResult.Error(exception)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, BusinessSearchResponse.Businesse>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//
//    private companion object {
//        const val INITIAL_PAGE_INDEX = 1
//    }
//}