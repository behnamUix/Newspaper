package com.behnamuix.newspaper.view.nav

import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.behnamuix.newspaper.service.NewsForgroundService
import com.behnamuix.newspaper.view.comp.MyBottomBar
import com.behnamuix.newspaper.view.comp.MyTopBar
import com.behnamuix.newspaper.view.tabs.HomeTab

@OptIn(ExperimentalMaterial3Api::class)

class MainScreen : Screen {
    @Composable
    override fun Content() {
        var context = LocalContext.current
        TabNavigator(HomeTab) { tabNavigator ->
            Scaffold(
                topBar = {
                    MyTopBar(
                        "Newspaper", onLiveClick = {
                            val intent = Intent(context, NewsForgroundService::class.java)

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                context.startForegroundService(intent)
                                Toast.makeText(context, "news service started", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                context.startService(intent)
                            }
                        },
                        onStopServiceClick = {
                            val intent = Intent(context, NewsForgroundService::class.java)
                            context.stopService(intent)

                        })
                },
                bottomBar = {
                    MyBottomBar(tabNavigator)
                }
            ) { padding ->
                Box(Modifier.padding(padding)) {
                    CurrentTab()
                }
            }
        }
    }
}
