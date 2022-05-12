package com.elnaggar.instabugtask.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elnaggar.instabugtask.databinding.ListItemBinding

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WordCount>() {
    override fun areItemsTheSame(oldItem: WordCount, newItem: WordCount): Boolean {
        return oldItem.word == newItem.word
    }

    override fun areContentsTheSame(oldItem: WordCount, newItem: WordCount): Boolean {
        return oldItem == newItem
    }

}


class WordsCountAdapter : ListAdapter<WordCount, WordCountViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordCountViewHolder {
        return WordCountViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordCountViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


class WordCountViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: WordCount) {
        binding.wordTextView.text = item.word
        binding.countTextView.text = item.count
    }


    companion object {
        fun create(parent: ViewGroup): WordCountViewHolder {
            val binding =
                ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return WordCountViewHolder(binding)
        }
    }

}

