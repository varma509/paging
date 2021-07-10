package com.eutor.core.baseui

import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eutor.core.listener.OnItemClickListener

abstract class BaseAdapter<T : Any, U>(callBackListener: DiffUtil.ItemCallback<T>) :
    PagingDataAdapter<T, BaseAdapter.Holder<T>>(callBackListener) {

    abstract class Holder<T>(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(listener: OnItemClickListener<T>, item: T, adapterPosition: Int)
    }
}

