package com.behnamuix.newspaper.view.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.behnamuix.newspaper.R
import com.behnamuix.newspaper.view.screen.HomeScreen
import com.behnamuix.newspaper.view.screen.SettingSc
import com.behnamuix.newspaper.view.screen.SettingScreen

object SettingTab : Tab {
    override val options: TabOptions
        @Composable
        get(){
            val title="settings"
            val icon= painterResource(R.drawable.icon_setting)
            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {


       Navigator(SettingSc)
    }

}