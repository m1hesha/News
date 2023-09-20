package com.example.news.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable





@Composable
fun NewsAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content
    )
}