package com.storemate.storemate.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eutor.core.baseui.BaseAdapter
import com.eutor.core.data.models.search.SearchItem
import com.eutor.core.listener.OnItemClickListener
import com.storemate.storemate.databinding.RepoItemBinding


class GitMembersAdapter(var listener: OnItemClickListener<SearchItem>) :
        BaseAdapter<SearchItem, BaseAdapter.Holder<SearchItem>>(GitCallBack()) {

    override fun onBindViewHolder(holder: Holder<SearchItem>, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(listener, item, position)
        }
    }

    private class SearchViewHolder(val binding: RepoItemBinding) : BaseAdapter.Holder<SearchItem>(binding) {
        override fun bind(listener: OnItemClickListener<SearchItem>, item: SearchItem,
                          adapterPosition: Int) {
            binding.gitMember = item
            binding.listener = listener
            binding.position = adapterPosition
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder<SearchItem> {
        return SearchViewHolder(
                RepoItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    class GitCallBack : DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem.fullName == newItem.fullName && oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem== newItem
        }
    }
}