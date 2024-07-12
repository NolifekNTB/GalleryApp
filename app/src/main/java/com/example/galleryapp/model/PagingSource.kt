package com.example.galleryapp.model

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.galleryapp.data.remote.ResponseService
import com.example.galleryapp.data.remote.dto.ArticleDto

class PagingSource(
    private val api: ResponseService,
) : PagingSource<Int, ArticleDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        return try {
            val nextPageNumber = params.key ?: 1
            Log.d("PagingSource", "Loading page $nextPageNumber with size ${params.loadSize}")
            val response = api.getResponse(nextPageNumber, params.loadSize).articles

            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            Log.e("PagingSource", "Error loading page $params", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}