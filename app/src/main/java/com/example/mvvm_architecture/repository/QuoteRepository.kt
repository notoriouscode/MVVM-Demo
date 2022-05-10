package com.example.mvvm_architecture.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_architecture.api.QuoteService
import com.example.mvvm_architecture.db.QuoteDatabase
import com.example.mvvm_architecture.models.QuoteList
import com.example.mvvm_architecture.utils.NetworkUtils

class QuoteRepository(
    private val qutoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<ApiResponse<QuoteList>>()

    val quotes: LiveData<ApiResponse<QuoteList>>
        get() = quotesLiveData

    suspend fun getQutoes(page: Int) {

        if (NetworkUtils.isOnline(applicationContext)) {

            try {
                val result = qutoteService.getQuotes(page)
                if (result.body() != null) {
                    Log.d("networkstatus", "Online")
                    quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                    quotesLiveData.postValue(ApiResponse.Sucess(result.body()))
                }
                else{
                    quotesLiveData.postValue(ApiResponse.Error("API error"))
                }
            } catch (e: Exception) {
                quotesLiveData.postValue(ApiResponse.Error(e.message.toString()))
            }

        } else {
            Log.d("networkstatus", "Offline")
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteList(1, 1, 1, quotes, 1, 1);
            quotesLiveData.postValue(ApiResponse.Sucess(quoteList))
        }
    }
}