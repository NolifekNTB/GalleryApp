package com.example.galleryapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.galleryapp.data.remote.PostsServiceImpl
import com.example.galleryapp.data.remote.dto.Article
import com.example.galleryapp.presentation.PagingSource
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

class PagingRepository(private val ktorClient: HttpClient) {

    private val api = PostsServiceImpl(ktorClient)
    fun getYourDataStream(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 3,
                enablePlaceholders = false,
                initialLoadSize = 10),
            pagingSourceFactory = { PagingSource(api) }
        ).flow
    }
}