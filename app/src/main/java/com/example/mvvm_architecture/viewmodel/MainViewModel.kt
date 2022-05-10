package com.example.mvvm_architecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_architecture.models.QuoteList
import com.example.mvvm_architecture.repository.ApiResponse
import com.example.mvvm_architecture.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQutoes(1)
        }
    }

    val quotes: LiveData<ApiResponse<QuoteList>>
        get() = repository.quotes
}