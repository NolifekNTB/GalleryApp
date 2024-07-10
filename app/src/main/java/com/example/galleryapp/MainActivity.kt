package com.example.galleryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galleryapp.data.remote.PostsService
import com.example.galleryapp.data.remote.dto.PostResponse
import com.example.galleryapp.ui.theme.GalleryAppTheme

class MainActivity : ComponentActivity() {

    private val service = PostsService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            val posts = produceState<PostResponse>(
                initialValue = PostResponse("", 0, listOf()),
                producer = {
                    value = service.getPosts()
                }
            )

            GalleryAppTheme {
                LazyColumn {
                    items(posts.value.articles) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = it.title, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = it.url, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}