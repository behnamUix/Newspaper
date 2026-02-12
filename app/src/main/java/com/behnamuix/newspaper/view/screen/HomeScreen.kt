package com.behnamuix.newspaper.view.screen

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.behnamuix.newspaper.database.dataStore.DataStoreManager
import com.behnamuix.newspaper.receiver.InternetBroadcastReceiver
import com.behnamuix.newspaper.room.toArticle
import com.behnamuix.newspaper.room.viewModel.NewsViewModel
import com.behnamuix.newspaper.utils.checkNet
import com.behnamuix.newspaper.view.comp.NewsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@RequiresApi(Build.VERSION_CODES.O)
object HomeSc : Screen {
    @Composable
    override fun Content() {
        HomeScreen()
    }

}

@Composable
fun HomeScreen(
    viewModel: NewsViewModel = getViewModel()
) {
    var nav = LocalNavigator.currentOrThrow
    val newsList by viewModel.news.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var context = LocalContext.current
    var netStatus by rememberSaveable { mutableStateOf(checkNet(context)) }
    var networkReceiver = InternetBroadcastReceiver { isConnected ->
        if (isConnected) {
            Log.d("NET", "Internet Connected")
            netStatus = true
        } else {
            Log.d("NET", "Internet Disconnected")
            netStatus = false
        }
    }

    LaunchedEffect(Unit) {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver, filter)
        if (netStatus) {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.refreshFromApi()
            }
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                snackbarHostState.showSnackbar("Please check your internet connection")
            }
        }


    }

    DisposableEffect(Unit) {
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver, filter)

        onDispose {
            context.unregisterReceiver(networkReceiver)
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (newsList.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                Column {
                    LazyColumn {
                        items(newsList) { it ->
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                NewsItem(
                                    it.toArticle(),
                                    onclick = {
                                        nav.parent?.parent?.push(DetailSc(article = it.toArticle()));
                                        DataStoreManager.saveLastOpenedNews(context, it.title)
                                    })
                            }
                        }
                    }
                }
            }


        }


    }

}
