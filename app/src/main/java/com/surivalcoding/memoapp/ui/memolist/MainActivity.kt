package com.surivalcoding.memoapp.ui.memolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.surivalcoding.memoapp.R
import com.surivalcoding.memoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // View 준비
        binding.recyclerView.layoutManager = LinearLayoutManager(this) // 2열

        // 어댑터(data - view 연결) 준비

        val adapter = MemoAdapter(
            onClicked = {
                binding.updateEditText.setText(it.content)

                viewModel.selectMemo(it)
            },
            onLongClicked = {
                viewModel.deleteMemo(it)
            }
        )

        // View에 Adapter 연결
        binding.recyclerView.adapter = adapter

        // UI 갱신
        viewModel.state.asLiveData().observe(this) { state ->
            adapter.submitList(state.items)
        }

        // 추가
        binding.addButton.setOnClickListener {
            viewModel.insertMemo(binding.contentEditText.text.toString())
            // 비우기
        }

        // 수정
        binding.updateButton.setOnClickListener {
            viewModel.updateMemo(binding.updateEditText.text.toString())
        }


    }
}