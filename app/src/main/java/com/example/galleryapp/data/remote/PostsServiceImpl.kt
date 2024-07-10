package com.example.galleryapp.data.remote

import com.example.galleryapp.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url


class PostsServiceImpl(
    private val client: HttpClient
) : PostsService {

    override suspend fun getPosts(): PostResponse {
        return client.get {
            url(HttpRoutes.POSTS)
            parameter("apiKey", "750383b1af894d659264059445fba95b")
            parameter("q", "Kotlin")
            parameter("pagesize", 10)
        }.body()
    }
}