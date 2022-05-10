package com.example.mvvm_architecture

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_architecture.databinding.RecyclerviewItemBinding
import com.example.mvvm_architecture.models.Result
import com.example.mvvm_architecture.utils.RecyclerViewClickListener

class QuoteListAdapter(private val quoteList: List<Result>) :
    ListAdapter<Result, QuoteListAdapter.QuoteListViewHolder>(DiffUtil()) {

    private lateinit var binding: RecyclerviewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteListViewHolder {
        binding =
            RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteListViewHolder, position: Int) {
        val quote = quoteList[position]
        holder.bind(quote)
    }

    override fun getItemCount(): Int = quoteList.size

    inner class QuoteListViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(quote: Result) {
            binding.quotes = quote
        }
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.quoteId == newItem.quoteId
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
}

