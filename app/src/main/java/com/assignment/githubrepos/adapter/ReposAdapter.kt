package com.assignment.githubrepos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.assignment.githubrepos.R
import com.assignment.githubrepos.model.GithubRepository
import com.bumptech.glide.Glide


class ReposAdapter() :
    ListAdapter<GithubRepository, ReposAdapter.RepoViewHolder>(RepoDiffCallback) {

    class RepoViewHolder(itemView: View) :
        ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.tvRepoTitle)
        private val ownerName: TextView = itemView.findViewById(R.id.tvOwnerName)
        private val ownerImage: ImageView = itemView.findViewById(R.id.ivOwner)
        private val descTextView: TextView = itemView.findViewById(R.id.tvRepoDesc)
        private val urlTextView: TextView = itemView.findViewById(R.id.tvRepoUrl)

        fun bind(repository: GithubRepository) {
            titleTextView.text = repository.name
            ownerName.text = repository.owner.login
            urlTextView.text = repository.html_url
            descTextView.text = repository.description
            Glide
                .with(itemView.context)
                .load(repository.owner.avatar_url)
                .circleCrop()
                .into(ownerImage);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)

    }
}
object RepoDiffCallback : DiffUtil.ItemCallback<GithubRepository>() {
    override fun areItemsTheSame(oldItem: GithubRepository, newItem: GithubRepository): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GithubRepository, newItem: GithubRepository): Boolean {
        return oldItem.html_url == newItem.html_url
    }
}