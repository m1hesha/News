import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.news.NewsItem

@Composable
fun NewsItemView(newsItem: NewsItem, onLikeClick: (Int, Int) -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = newsItem.title,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )

        val likes = remember(newsItem.id) { mutableStateOf(newsItem.likes) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Likes: ${likes.value ?: 0}")
            IconButton(
                onClick = {
                    likes.value = (likes.value ?: 0) + 1
                    onLikeClick(newsItem.id, likes.value ?: 0)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = null,
                    tint = if (likes.value ?: 0 > newsItem.likes) Color.Green else Color.Gray
                )
            }
        }
    }
}