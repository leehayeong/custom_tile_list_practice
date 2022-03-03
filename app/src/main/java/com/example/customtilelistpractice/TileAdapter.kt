package com.example.customtilelistpractice

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
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

    var longClickEvent: (() -> Unit)? = null
    var shakingAnimation: Animation? = null
    var isShaking: Boolean = false

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

    private fun startAnimation() {
        isShaking = true
        notifyDataSetChanged()
    }

    fun clearAnimation() {
        isShaking = false
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemTileBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            /**
             * setOnLongClickListener 반환값
             * false: default. 다음 이벤트 진행함. longClick 후 click 이벤트 실행됨.
             * true: 이벤트 종료됨. longClick 후 click 이벤트가 실행되지 않음. longClick 이벤트만 활성화하고 싶을 때 사용.
             */
            binding.root.setOnLongClickListener {
                longClickEvent?.invoke()
                startAnimation()
                return@setOnLongClickListener true
            }
        }

        fun bind(item: TileEntity) {
            binding.tile = item

            if (isShaking) {
                binding.root.startAnimation(shakingAnimation)
            } else {
                binding.root.clearAnimation()
            }
        }
    }
}
