package com.example.news

import androidx.lifecycle.ViewModel

data class NewsItem(val id: Int, val title: String, var likes: Int)

class NewsViewModel : ViewModel() {
    val newsItems = listOf(
        NewsItem(1, "Новость 1", 0),
        NewsItem(2, "Новость 2", 0),
        NewsItem(3, "Новость 3", 0),
        NewsItem(4, "Новость 4", 0),
        NewsItem(5, "Новость 5", 0),
        NewsItem(6, "Новость 6", 0),
        NewsItem(7, "Новость 7", 0),
        NewsItem(8, "Новость 8", 0),
        NewsItem(9, "Новость 9", 0),
        NewsItem(10, "Новость 10", 0),
    )
}
