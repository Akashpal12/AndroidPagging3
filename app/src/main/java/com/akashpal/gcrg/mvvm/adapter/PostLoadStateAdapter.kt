package com.akashpal.gcrg.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akashpal.gcrg.mvvm.R

class PostLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<PostLoadStateAdapter.LoadStateViewHolder>() {

    // ViewHolder class to hold and bind the views
    class LoadStateViewHolder(itemView: View, retry: () -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
        private val btnRetry: Button = itemView.findViewById(R.id.btnRetry)
        private val errorMsg: TextView = itemView.findViewById(R.id.errorMsg)

        init {
            btnRetry.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
            btnRetry.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = loadState is LoadState.Error
        }
    }

    // Create ViewHolder from the layout
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_load_state, parent, false)
        return LoadStateViewHolder(view, retry)
    }

    // Bind the data to the ViewHolder
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}