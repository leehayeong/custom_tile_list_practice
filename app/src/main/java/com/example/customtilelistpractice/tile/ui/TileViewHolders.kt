package com.example.customtilelistpractice.tile.ui

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.customtilelistpractice.common.bigShakingAnimation
import com.example.customtilelistpractice.databinding.ItemTileRectangleBinding
import com.example.customtilelistpractice.databinding.ItemTileSquareBinding
import com.example.customtilelistpractice.common.smallShakingAnimation
import com.example.customtilelistpractice.tile.model.RectangleTile
import com.example.customtilelistpractice.tile.model.SquareTile
import com.example.customtilelistpractice.tile.model.TileEntity

/**
 *  TileViewHolders.kt
 *
 *  Created by Hayeong Lee on 2022/04/08
 *  Copyright © 2022 Shinhan Bank. All rights reserved.
 */

abstract class TileViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(tileEntity: TileEntity, isEditMode: Boolean)
}

/**
 * 1X1 정사각형 ViewHolder
 */
class SquareTileViewHolder(
    private val binding: ItemTileSquareBinding,
    onLongClickListener: View.OnLongClickListener
) : TileViewHolder(binding) {

    init {
        binding.root.setOnLongClickListener(onLongClickListener)
    }

    override fun bind(tileEntity: TileEntity, isEditMode: Boolean) {
        val item = tileEntity as? SquareTile ?: return
        binding.tile = item
        binding.root.animation = bigShakingAnimation

        if (isEditMode) {
            binding.root.animation.start()
        } else {
            binding.root.clearAnimation()
        }
    }
}

/**
 * 3X1 직사각형 ViewHolder
 */
class RectangleTileViewHolder(
    private val binding: ItemTileRectangleBinding,
    onLongClickListener: View.OnLongClickListener
) : TileViewHolder(binding) {

    init {
        binding.root.setOnLongClickListener(onLongClickListener)
    }

    override fun bind(tileEntity: TileEntity, isEditMode: Boolean) {
        val item = tileEntity as? RectangleTile ?: return
        binding.tile = item
        binding.root.animation = smallShakingAnimation

        if (isEditMode) {
            binding.root.animation.start()
        } else {
            binding.root.clearAnimation()
        }
    }
}
