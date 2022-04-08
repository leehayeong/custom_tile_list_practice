package com.example.customtilelistpractice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.customtilelistpractice.databinding.ActivityMainRvTileBinding
import com.example.customtilelistpractice.databinding.ActivityMainTitleBinding
import com.example.customtilelistpractice.model.TileEntity

/**
 *  MainAdapter.kt
 *
 *  Created by Sangeun Lee on 2022/03/03
 *  Copyright © 2021 Shinhan Bank. All rights reserved.
 */

class MainAdapter : RecyclerView.Adapter<MainCommonViewHolder>() {
    private val list = listOf<Unit>(Unit, Unit)
    val tileAdapter by lazy { TileAdapter() }

    var startEditModeEventInAdapter: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCommonViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == TYPE_TITLE)
            TitleViewHolder(ActivityMainTitleBinding.inflate(inflater, parent, false))
        else
            TileViewHolder(ActivityMainRvTileBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MainCommonViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = list.size


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_TITLE
        else TYPE_TILE
    }

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_TILE = 1

        private val TEST_TILE_LIST = listOf(
            TileEntity("1", "A", 1, 1),
            TileEntity("2", "B", 2, 1),
            TileEntity("3", "C", 1, 1),
            TileEntity("5", "D", 3, 1),
            TileEntity("6", "E", 1, 1),
            TileEntity("7", "F", 2, 1),
            TileEntity("8", "G", 1, 1),
            TileEntity("9", "h", 3, 2),
            TileEntity("1", "A", 1, 1),
            TileEntity("2", "B", 2, 1),
            TileEntity("3", "C", 1, 1),
            TileEntity("5", "D", 3, 2),
            TileEntity("6", "E", 1, 1),
            TileEntity("7", "F", 2, 1),
            TileEntity("8", "G", 1, 1),
            TileEntity("9", "h", 3, 2),
        )
    }









    inner class TitleViewHolder(private val binding: ActivityMainTitleBinding) :
        MainCommonViewHolder(binding.root) {
        init {
            binding.okButton.setOnClickListener {
                tileAdapter.endEditMode()
                binding.okButton.visibility = View.GONE
            }
            startEditModeEventInAdapter = { binding.okButton.visibility = View.VISIBLE }
        }

        override fun bind() {}
    }

    inner class TileViewHolder(private val binding: ActivityMainRvTileBinding) :
        MainCommonViewHolder(binding.root) {

        init {
            tileAdapter.submitList(TEST_TILE_LIST)
        }

        override fun bind() {
            // TileItem 이 가진 span 값으로 타일들이 동적 크기를 가질 수 있도록 설정
            binding.tileList.layoutManager = GridLayoutManager(binding.tileList.context, 3).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return tileAdapter.currentList[position].widthSpan
                    }
                }
            }

            binding.tileList.adapter = tileAdapter.apply {
                // 타일이 long click 되면 편집가능한 모드가 되어서 확인 버튼이 보이도록 설정
                startEditModeEvent = startEditModeEventInAdapter
            }

            // 1. OnItemMoveListener 를 구현한 adapter 로 touchItemHelper Callback 을 생성하고
            // 2. ItemTouchHelper 를 만들어서
            // 3. recyclerView 에 attach
            val callback: ItemTouchHelper.Callback = ItemTouchHelperCallback(tileAdapter)
            val touchHelper = ItemTouchHelper(callback)
            touchHelper.attachToRecyclerView(binding.tileList)
        }
    }

}

abstract class MainCommonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind()
}