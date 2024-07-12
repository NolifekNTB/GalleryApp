package com.example.galleryapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto (
    val title: String,
    val url: String,
    val content: String
)