package com.akashpal.gcrg.mvvm.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akashpal.gcrg.mvvm.R
import com.akashpal.gcrg.mvvm.models.PostModelItem


class PostAdapter : ListAdapter<PostModelItem, PostAdapter.PostViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        if (post!=null){
            holder.titleTextView.text=post.title
            holder.bodyTextView.text=post.title
        }

    }


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val titleTextView: TextView = itemView.findViewById(R.id.tvTitle)
         val bodyTextView: TextView = itemView.findViewById(R.id.tvBody)

    }

    class DiffCallback : DiffUtil.ItemCallback<PostModelItem>() {
        override fun areItemsTheSame(oldItem: PostModelItem, newItem: PostModelItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PostModelItem, newItem: PostModelItem): Boolean =
            oldItem == newItem
    }
}
