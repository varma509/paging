package com.eutor.core.listener

interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}