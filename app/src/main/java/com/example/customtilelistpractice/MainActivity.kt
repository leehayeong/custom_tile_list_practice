package com.example.customtilelistpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customtilelistpractice.adapter.MainAdapter
import com.example.customtilelistpractice.databinding.ActivityMainRvBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainRvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.mainRvContainer.adapter = MainAdapter()
    }
}
