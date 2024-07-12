package com.example.galleryapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.galleryapp.data.remote.dto.ArticleDto
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsScreen() {
    val viewModel: PagingViewModel = koinViewModel()
    val lazyPagingItems = viewModel.dataStream.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Your Title") })
        }
    ) {
        LazyColumn {
            items(lazyPagingItems.itemCount) { index ->
                lazyPagingItems[index]?.let {
                    YourItemComposable(index, it)
                }
            }

            lazyPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        // Show loading UI
                        item { LoadingItem() }
                    }
                    loadState.append is LoadState.Loading -> {
                        // Show loading UI
                        item { LoadingItem() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        // Show error UI
                        val e = lazyPagingItems.loadState.refresh as LoadState.Error
                        item { ErrorItem(e.error.localizedMessage!!, onRetry = { retry() }) }
                    }
                    loadState.append is LoadState.Error -> {
                        // Show error UI
                        val e = lazyPagingItems.loadState.append as LoadState.Error
                        item { ErrorItem(e.error.localizedMessage!!, onRetry = { retry() }) }
                    }
                }
            }
        }
    }
}

@Composable
fun YourItemComposable(index: Int, item: ArticleDto) {
    // Implement the UI for your item
    Text(text = "$index + ${item.title}", fontSize = 20.sp)
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = item.content, fontSize = 15.sp)
}

@Composable
fun LoadingItem() {
    // Implement the loading UI
    CircularProgressIndicator()
}

@Composable
fun ErrorItem(message: String, onRetry: () -> Unit) {
    // Implement the error UI
    Column {
        Text(text = message)
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}