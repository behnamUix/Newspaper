package com.behnamuix.newspaper.view.tabs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.behnamuix.newspaper.R
import com.behnamuix.newspaper.view.screen.HomeSc

object HomeTab : Tab {
    override val options: TabOptions
        @Composable

        get() {
            val title = "home"
            val icon = painterResource(R.drawable.icon_home)
            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        // صفحه اصلی اخبار
        Navigator(HomeSc)
    }

}