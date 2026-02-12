package com.behnamuix.newspaper.view.comp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.behnamuix.newspaper.api.model.Article
import com.behnamuix.newspaper.utils.convertDate
import com.behnamuix.newspaper.view.screen.DetailSc

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsItem(newsList: Article, onclick:()->Unit) {
    Column(Modifier
        .clickable(onClick = {
            onclick()
        })
        .padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            color = MaterialTheme.colorScheme.onBackground,
            text = newsList.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Row() {
            Text(
                newsList.source.name,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Thin,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.weight(1f))
            Text(
                convertDate(newsList.publishedAt),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Thin,
                color = MaterialTheme.colorScheme.onSurface
            )

        }
        HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(8.dp))

    }


}