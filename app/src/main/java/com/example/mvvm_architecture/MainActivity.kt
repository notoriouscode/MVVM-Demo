package com.example.mvvm_architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_architecture.adapter.QuoteListAdapter
import com.example.mvvm_architecture.application.Application
import com.example.mvvm_architecture.databinding.ActivityMainBinding
import com.example.mvvm_architecture.repository.ApiResponse
import com.example.mvvm_architecture.viewmodel.MainViewModel
import com.example.mvvm_architecture.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val repository = (application as Application).quoteRepository

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {

            when (it) {
                is ApiResponse.Loading -> {
                }
                is ApiResponse.Sucess -> {
                    it.data?.let { quoteList ->
                        binding.rvQuotes.adapter = QuoteListAdapter(quoteList.results) {view,position->
                            when(view.id){
                                R.id.tv_author->{
                                    Toast.makeText(this,"Clicked $position",Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        binding.rvQuotes.layoutManager = LinearLayoutManager(this)
                    }
                }
                is ApiResponse.Error -> {
                    Toast.makeText(applicationContext, "Some error occured.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}