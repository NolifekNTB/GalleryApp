package com.example.galleryapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.galleryapp.data.remote.ResponseService
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val postsService: ResponseService by inject()

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