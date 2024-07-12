package com.example.galleryapp.presentation

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.galleryapp.data.remote.PostsServiceImpl
import com.example.galleryapp.data.remote.dto.Article

class PagingSource(
    private val api: PostsServiceImpl,
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val nextPageNumber = params.key ?: 1
            Log.d("PagingSource", "Loading page $nextPageNumber with size ${params.loadSize}")
            val response = api.getPosts(nextPageNumber, params.loadSize).articles

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

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}