package com.example.customtilelistpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.customtilelistpractice.databinding.ActivityMainBinding
import com.example.customtilelistpractice.model.TileEntity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tileAdapter by lazy { TileAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
        setTileList()
    }

    private fun setRecyclerView() {
        binding.tileList.apply {
            adapter = tileAdapter.apply {
                longClickEvent = {
                    Log.d("click", it.id + " " + it.name)
                }
            }
        }
    }

    private fun setTileList() {
        tileAdapter.submitList(TEST_TILE_LIST)
    }

    companion object {
        private val TEST_TILE_LIST = listOf(
            TileEntity("1", "A"),
            TileEntity("2", "B"),
            TileEntity("3", "C"),
            TileEntity("4", "D"),
        )
    }
}
