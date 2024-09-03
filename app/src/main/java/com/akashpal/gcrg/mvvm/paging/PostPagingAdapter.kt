package com.akashpal.gcrg.mvvm.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akashpal.gcrg.mvvm.R
import com.akashpal.gcrg.mvvm.models.PostModelItem

class PostPagingAdapter : PagingDataAdapter<PostModelItem, PostPagingAdapter.PostViewHolder>(POST_COMPARATOR) {

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.tvTitle)
        private val bodyTextView: TextView = view.findViewById(R.id.tvBody)

        fun bind(post: PostModelItem) {
            titleTextView.text = post.title
            bodyTextView.text = post.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        if (post != null) {
            holder.bind(post)
        }
    }

    companion object {
        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<PostModelItem>() {
            override fun areItemsTheSame(oldItem: PostModelItem, newItem: PostModelItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PostModelItem, newItem: PostModelItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}