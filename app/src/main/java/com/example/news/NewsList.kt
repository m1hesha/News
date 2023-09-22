package com.example.news

import NewsItemView
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlin.random.Random


@SuppressLint("UnrememberedMutableState")
@Composable
fun NewsList() {
    val viewModel: NewsViewModel = viewModel()
    val displayedIndices = remember { mutableStateListOf<Int>() }
    val likesMap = mutableStateMapOf<Int, Int>()

    if (displayedIndices.isEmpty()) {
        // При первом запуске добавляем 4 уникальных случайных индекса
        val uniqueIndices = mutableSetOf<Int>()
        while (uniqueIndices.size < 4) {
            val randomIndex = Random.nextInt(viewModel.newsItems.size)
            if (uniqueIndices.add(randomIndex)) {
                displayedIndices.add(randomIndex)
            }
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000L)
            var randomIndex = Random.nextInt(viewModel.newsItems.size)
            val updatedIndices = displayedIndices.toMutableList()
            val randomDisplayedIndex = Random.nextInt(4)

            // Проверяем, чтобы новый индекс был уникальным
            while (updatedIndices.contains(randomIndex)) {
                val newIndex = Random.nextInt(viewModel.newsItems.size)
                if (!updatedIndices.contains(newIndex)) {
                    randomIndex = newIndex
                }
            }

            updatedIndices[randomDisplayedIndex] = randomIndex

            displayedIndices.clear()
            displayedIndices.addAll(updatedIndices)

            // Обнуляем лайки для новой новости
            likesMap[viewModel.newsItems[randomIndex].id] = 0
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    // Левая верхняя новость
                    val indexTopLeft = displayedIndices.getOrElse(0) { -1 }
                    if (indexTopLeft != -1) {
                        val newsItem = viewModel.newsItems[indexTopLeft]
                        val likes = likesMap.getOrPut(newsItem.id) { newsItem.likes }
                        NewsItemView(newsItem) { id, updatedLikes ->
                            likesMap[id] = updatedLikes
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    // Правая верхняя новость
                    val indexTopRight = displayedIndices.getOrElse(1) { -1 }
                    if (indexTopRight != -1) {
                        val newsItem = viewModel.newsItems[indexTopRight]
                        val likes = likesMap.getOrPut(newsItem.id) { newsItem.likes }
                        NewsItemView(newsItem) { id, updatedLikes ->
                            likesMap[id] = updatedLikes
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    // Левая нижняя новость
                    val indexBottomLeft = displayedIndices.getOrElse(2) { -1 }
                    if (indexBottomLeft != -1) {
                        val newsItem = viewModel.newsItems[indexBottomLeft]
                        val likes = likesMap.getOrPut(newsItem.id) { newsItem.likes }
                        NewsItemView(newsItem) { id, updatedLikes ->
                            likesMap[id] = updatedLikes
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    // Правая нижняя новость
                    val indexBottomRight = displayedIndices.getOrElse(3) { -1 }
                    if (indexBottomRight != -1) {
                        val newsItem = viewModel.newsItems[indexBottomRight]
                        val likes = likesMap.getOrPut(newsItem.id) { newsItem.likes }
                        NewsItemView(newsItem) { id, updatedLikes ->
                            likesMap[id] = updatedLikes
                        }
                    }
                }
            }
        }
    }
}
