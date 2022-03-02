package com.example.customtilelistpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.customtilelistpractice.databinding.ItemTileBinding
import com.example.customtilelistpractice.model.TileEntity

/**
 *  MainAdapter.kt
 *
 *  Created by Hayeong Lee on 2022/03/02
 *  Copyright © 2021 Shinhan Bank. All rights reserved.
 */

class TileAdapter : ListAdapter<TileEntity, TileAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    private class DiffCallback : DiffUtil.ItemCallback<TileEntity>() {
        override fun areItemsTheSame(oldItem: TileEntity, newItem: TileEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TileEntity, newItem: TileEntity): Boolean =
            oldItem == newItem
    }

    inner class ViewHolder(private val binding: ItemTileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TileEntity) {
            binding.tile = item
        }
    }
}
