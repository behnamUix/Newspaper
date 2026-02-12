package com.behnamuix.newspaper.koin

import com.behnamuix.newspaper.room.NewsDatabase
import com.behnamuix.newspaper.room.repository.NewsRepository
import com.behnamuix.newspaper.room.viewModel.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {

    single { NewsDatabase.getInstance(androidContext()) }
    single { get<NewsDatabase>().newsDao() }
    single { NewsRepository(get()) }
    viewModel { NewsViewModel(get()) }

}