package com.example.customtilelistpractice.tile.ui

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 *  ItemTouchHelperCallback.kt
 *
 *  Created by Hayeong Lee on 2022/03/03
 *  Copyright © 2021 Shinhan Bank. All rights reserved.
 */

class ItemTouchHelperCallback(
    private val itemMoveListener: OnItemMoveListener
) : ItemTouchHelper.Callback() {

    /**
     * 이벤트의 방향 설정, 어느 방향으로 움직일지에 따라 flag 정의
     */
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or
                ItemTouchHelper.DOWN or
                ItemTouchHelper.LEFT or
                ItemTouchHelper.RIGHT

        // 선택된 아이템이 이동 가능한 상태이면 (전체 아이템이 shaking 되고 있는 상태) 움직이는 이벤트 flag 전달
        return if (itemMoveListener.isEditMode) makeMovementFlags(dragFlags, 0)
        else makeMovementFlags(0, 0)
    }

    /**
     * 위치 변경에 대한 이벤트를 받음 (어느 위치에서 어느 위치로 변경하는지)
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        itemMoveListener.onItemMoved(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    /**
     * 좌우 스와이프
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    /**
     * longPress event 를 사용하고 싶을 때 enable true
     */
    override fun isLongPressDragEnabled(): Boolean = true

    /**
     * 아이템 이동 리스너
     * ItemTouchCallback 을 적용하고 싶은 adapter 에서 override 에서 구현 (현재는 TileAdapter 에서 구현)
     */
    interface OnItemMoveListener {
        var isEditMode: Boolean
        fun onItemMoved(fromPosition: Int, toPosition: Int)
    }
}
