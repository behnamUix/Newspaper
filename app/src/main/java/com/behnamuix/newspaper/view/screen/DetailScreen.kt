package com.behnamuix.newspaper.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import coil.compose.AsyncImage
import com.behnamuix.newspaper.api.model.Article
import com.behnamuix.newspaper.utils.goToArticle

data class DetailSc(val article: Article) : Screen {
    @Composable
    override fun Content() {
        DetailScreen(article)
    }


}

@Composable
fun DetailScreen(article: Article) {
    var nav = LocalNavigator.current
    var context = LocalContext.current
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                AsyncImage(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp,
                                topStart = 8.dp,
                                topEnd = 8.dp
                            )
                        ),
                    contentScale = ContentScale.FillBounds,
                    model = article.urlToImage,
                    contentDescription = ""
                )
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
                    TextButton(modifier = Modifier.padding(16.dp), onClick = { nav?.pop() }) {
                        Text(
                            "back"
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.padding(8.dp),

                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {


                Column(Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        color = MaterialTheme.colorScheme.onBackground,
                        text = article.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = article.content ?: "",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Thin,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                }

            }
            Spacer(Modifier.weight(1f))
            Column(Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "witer:" + article.author ?: "",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Thin,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
                )
                Text(
                    text = "source:" + article.source.name ?: "",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Thin,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
                )
            }


            Button(
                shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, 8.dp),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    goToArticle(context = context, url = article.url)
                }) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "open full article",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }


    }

}

