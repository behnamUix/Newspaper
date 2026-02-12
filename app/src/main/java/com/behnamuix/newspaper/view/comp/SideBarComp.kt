package com.behnamuix.newspaper.view.comp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.behnamuix.newspaper.R
import com.behnamuix.newspaper.view.tabs.HomeTab
import com.behnamuix.newspaper.view.tabs.CategoryTab
import com.behnamuix.newspaper.view.tabs.SettingTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    text: String,
    onNotifClick: () -> Unit = {},
    onLiveClick: () -> Unit = {},
    onStopServiceClick: () -> Unit = {}
) {
    var serviceClick by remember { mutableStateOf(false) }
    var checkService = if (serviceClick) {
        onLiveClick()
        colorScheme.secondary
    } else {
        onStopServiceClick()
        colorScheme.secondary.copy(0.5f)
    }
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(16.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {
            Text(
                text = text,
                color = colorScheme.onBackground,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = onNotifClick) {
                Icon(

                    painter = painterResource(R.drawable.icon_notif),
                    contentDescription = "notif"
                )
            }
        },
        actions = {
            OutlinedCard (shape = CircleShape, border = BorderStroke(width = 1.dp, color = checkService)){
                IconButton(onClick = { serviceClick = !serviceClick }) {
                    Icon(
                        tint = checkService,
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(R.drawable.icon_live),
                        contentDescription = "live"
                    )
                }
            }
        }
    )
}

@Composable
fun MyBottomBar(tabNavigator: TabNavigator) {
    NavigationBar(
        containerColor = colorScheme.background,
        modifier = Modifier.clip(RoundedCornerShape(16.dp))
    ) {
        listOf(CategoryTab, HomeTab, SettingTab).forEach { tab ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color.Red,      // همون قرمز انتخاب‌شده
                    selectedTextColor = Color.Red
                ),
                selected = tabNavigator.current == tab,
                onClick = { tabNavigator.current = tab },
                icon = {
                    Icon(
                        painter = tab.options.icon!!,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(tab.options.title)
                }
            )
        }
    }
}
