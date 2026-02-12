package com.behnamuix.newspaper.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.behnamuix.newspaper.database.dataStore.DataStoreManager
import com.behnamuix.newspaper.ui.theme.ThemeState

object SettingSc : Screen {
    @Composable
    override fun Content() {

        SettingScreen()

    }


}

@Composable
fun SettingScreen(modifier: Modifier = Modifier) {
    var context = LocalContext.current
    val lastOpenedNews by DataStoreManager.getLastOpenedNews(context)
        .collectAsState(initial = "Loading...")
    val darkModeFlow = DataStoreManager.getDarkMode(context)
    val isDarkMode = darkModeFlow.collectAsState(initial = false)
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Text("dark mode")
            Spacer(Modifier.weight(1f))
            Switch(
                checked = isDarkMode.value,
                onCheckedChange = {
                    ThemeState.isDark = it
                    DataStoreManager.saveDarkMode(context, it)
                }
            )
        }
        Card(
            Modifier.padding(16.dp),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    color = MaterialTheme.colorScheme.onBackground,
                    text = "last opened news title",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = lastOpenedNews,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.3f)
                )
            }
        }

    }


}