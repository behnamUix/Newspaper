package com.behnamuix.newspaper.room.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.newspaper.api.loadNews
import com.behnamuix.newspaper.api.model.toEntity
import com.behnamuix.newspaper.room.repository.NewsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repo: NewsRepository
) : ViewModel() {

    //tabdil stateflow be state
    val news = repo.getNewsFromDb().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun refreshFromApi() {
        viewModelScope.launch {
            repo.refreshNews()
        }
    }
}
