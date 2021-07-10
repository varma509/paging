package com.storemate.storemate.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eutor.core.databinding.LoadStateViewBinding


class MovieLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        holder.bind(loadState)

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {

        val binding = LoadStateViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

            return LoadStateViewHolder(binding)
    }

    inner class LoadStateViewHolder(private val viewBinding: LoadStateViewBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(loadState: LoadState) = with(viewBinding) {


            loadStateRetry.isVisible = loadState !is LoadState.Loading
            loadStateErrorMessage.isVisible = loadState !is LoadState.Loading
            loadStateProgress.isVisible = loadState is LoadState.Loading

            if (loadState is LoadState.Error){
                loadStateErrorMessage.text = loadState.error.localizedMessage
            }

            loadStateRetry.setOnClickListener {
                retry.invoke()
            }
        }
    }
}