package com.example.customtilelistpractice.tile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.customtilelistpractice.databinding.ItemTileRectangleBinding
import com.example.customtilelistpractice.databinding.ItemTileSquareBinding
import com.example.customtilelistpractice.tile.model.TileEntity
import com.example.customtilelistpractice.tile.model.TileType
import java.util.*

/**
 *  MainAdapter.kt
 *
 *  Created by Hayeong Lee on 2022/03/02
 *  Copyright © 2021 Shinhan Bank. All rights reserved.
 */

class TileAdapter : ListAdapter<TileEntity, TileViewHolder>(DiffCallback()),
    ItemTouchHelperCallback.OnItemMoveListener {

    var startEditModeEvent: (() -> Unit)? = null    // 타일 아이템을 길게 클릭했을 때 이벤트
    override var isEditMode: Boolean = false        // 현재 편집 모드인지 (= 아이템이 움직일 수 있는지)

    /**
     * setOnLongClickListener 반환값
     * false: default. 다음 이벤트 진행함. longClick 후 click 이벤트 실행됨.
     * true: 이벤트 종료됨. longClick 후 click 이벤트가 실행되지 않음. longClick 이벤트만 활성화하고 싶을 때 사용.
     */
    private val longClickListener = View.OnLongClickListener {
        // 편집모드라면 long pressed 가 감지되었을 때 애니메이션을 동작하게 하면 안되므로, 편집모드가 아닐 때에만 편집모드 시작하여 애니메이션 등이 동작하도록 함
        if (!isEditMode) startEditMode()
        return@OnLongClickListener true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TileType.SQUARE.ordinal -> {
                SquareTileViewHolder(
                    ItemTileSquareBinding.inflate(inflater, parent, false),
                    longClickListener
                )
            }
            TileType.RECTANGLE.ordinal -> {
                RectangleTileViewHolder(
                    ItemTileRectangleBinding.inflate(inflater, parent, false),
                    longClickListener
                )
            }
            else -> {
                SquareTileViewHolder(
                    ItemTileSquareBinding.inflate(inflater, parent, false),
                    longClickListener
                )
            }
        }
    }

    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        holder.bind(currentList[position], isEditMode)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].type.ordinal
    }

    /**
     * ItemTouchHelperCallback 에서 drag and drop 으로 아이템의 위치 변경이 감지되었을 때 (= onMove 가 호출되었을 때) 이벤트 구현
     */
    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        // 편집모드일 때에만 아이템 위치 변경이 가능해야하므로, 편집모드가 아닐때에는 함수 종료
        if (!isEditMode) return

        // diffUtil 의 currentList 는 수정이 불가능한 List 타입이기 때문에 mutableList 로 변경 후에 아이템 위치를 swap 하고 submitList 수행
        val newList = currentList.toMutableList()
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(newList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(newList, i, i - 1)
            }
        }
        submitList(newList)
    }

    private fun startEditMode() {
        isEditMode = true
        startEditModeEvent?.invoke()
        notifyDataSetChanged()
    }

    fun endEditMode() {
        isEditMode = false
        notifyDataSetChanged()
    }

    private class DiffCallback : DiffUtil.ItemCallback<TileEntity>() {
        override fun areItemsTheSame(oldItem: TileEntity, newItem: TileEntity): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: TileEntity, newItem: TileEntity): Boolean =
            oldItem == newItem
    }
}
