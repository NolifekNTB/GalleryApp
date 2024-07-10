package com.example.galleryapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)