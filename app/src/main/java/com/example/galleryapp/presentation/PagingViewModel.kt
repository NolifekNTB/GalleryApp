package com.example.galleryapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.galleryapp.data.PagingRepository
import com.example.galleryapp.data.remote.dto.ArticleDto
import kotlinx.coroutines.flow.Flow

class PagingViewModel(
    private val repository: PagingRepository
): ViewModel() {
    val dataStream: Flow<PagingData<ArticleDto>> = repository.getYourDataStream().cachedIn(viewModelScope)
}