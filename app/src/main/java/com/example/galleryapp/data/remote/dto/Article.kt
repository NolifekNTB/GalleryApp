package com.example.galleryapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Article (
    val title: String,
    val url: String,
    val content: String
)