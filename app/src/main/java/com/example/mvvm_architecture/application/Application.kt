package com.example.mvvm_architecture.application

import android.app.Application
import com.example.mvvm_architecture.api.RetrofitHelper
import com.example.mvvm_architecture.db.QuoteDatabase
import com.example.mvvm_architecture.repository.QuoteRepository

class Application : Application() {
    lateinit var quoteRepository: QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initilize()
    }

    private fun initilize() {
        val quoteDatabase=QuoteDatabase.getDatabase(applicationContext)
        val quoteService = RetrofitHelper.getInstance()
        quoteRepository = QuoteRepository(quoteService,quoteDatabase,applicationContext)
    }
}