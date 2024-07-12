package com.example.galleryapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galleryapp.data.remote.PostsServiceImpl
import com.example.galleryapp.data.remote.dto.PostResponse
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val postsService: PostsServiceImpl by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            NewsScreen()
            /*val posts = produceState<PostResponse>(
                initialValue = PostResponse("", 0, listOf()),
                producer = {
                    value = postsService.getPosts()
                }
            )

            LazyColumn {
                items(posts.value.articles) { post ->
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = post.title, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = post.content)
                    }
                }
            }
             */
        }
    }
}