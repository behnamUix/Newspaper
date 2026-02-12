package com.behnamuix.newspaper.view.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.behnamuix.newspaper.R
import com.behnamuix.newspaper.view.screen.CategoryScreen

object CategoryTab: Tab {
    override val options: TabOptions
        @Composable
        get(){
            val title="category"
            val icon= painterResource(R.drawable.icon_category)
            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }


    @Composable
    override fun Content() {
        // صفحه اصلی اخبار
        CategoryScreen()
    }

}