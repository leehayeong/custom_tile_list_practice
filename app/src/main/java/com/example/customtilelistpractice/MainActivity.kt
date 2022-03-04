package com.example.customtilelistpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.customtilelistpractice.adapter.ItemTouchHelperCallback
import com.example.customtilelistpractice.adapter.TileAdapter
import com.example.customtilelistpractice.databinding.ActivityMainBinding
import com.example.customtilelistpractice.model.TileEntity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tileAdapter by lazy { TileAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
        setRecyclerView()
        setTileList()
    }

    private fun setClickListener() {
        binding.okButton.setOnClickListener {
            tileAdapter.endEditMode()
            binding.okButton.visibility = View.GONE
        }
    }

    private fun setRecyclerView() {
        // TileItem 이 가진 span 값으로 타일들이 동적 크기를 가질 수 있도록 설정
        binding.tileList.layoutManager = GridLayoutManager(this, 3).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return tileAdapter.currentList[position].widthSpan
                }
            }
        }

        binding.tileList.adapter = tileAdapter.apply {
            // 타일이 long click 되면 편집가능한 모드가 되어서 확인 버튼이 보이도록 설정
            startEditModeEvent = { binding.okButton.visibility = View.VISIBLE }
        }

        // 1. OnItemMoveListener 를 구현한 adapter 로 touchItemHelper Callback 을 생성하고
        // 2. ItemTouchHelper 를 만들어서
        // 3. recyclerView 에 attach
        val callback: ItemTouchHelper.Callback = ItemTouchHelperCallback(tileAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.tileList)
    }

    private fun setTileList() {
        tileAdapter.submitList(TEST_TILE_LIST)
    }

    companion object {
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
}
