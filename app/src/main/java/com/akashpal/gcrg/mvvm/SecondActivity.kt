package com.akashpal.gcrg.mvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.akashpal.gcrg.mvvm.adapter.PostLoadStateAdapter
import com.akashpal.gcrg.mvvm.api.RetrofitClient
import com.akashpal.gcrg.mvvm.databinding.ActivitySecondBinding
import com.akashpal.gcrg.mvvm.paging.PostPagingAdapter
import com.akashpal.gcrg.mvvm.repository.PostPagingRepository
import com.akashpal.gcrg.mvvm.viewModel.PostPagingViewModel
import com.akashpal.gcrg.mvvm.viewModel.PostPagingViewModelFactory


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var mainViewModel: PostPagingViewModel
    private lateinit var postAdapter: PostPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Initialize Retrofit and Repository
        val postService = RetrofitClient.getClient()
        val repository = PostPagingRepository(postService)

        // Set up RecyclerView with PagingDataAdapter and LoadStateAdapter
        setupRecyclerView()

        // Initialize ViewModel
        mainViewModel = ViewModelProvider(
            this,
            PostPagingViewModelFactory(repository)
        ).get(PostPagingViewModel::class.java)


        mainViewModel.posts.observe(this, Observer { pagingData ->
            postAdapter.submitData(lifecycle, pagingData)
        })
    }
    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SecondActivity)
            postAdapter = PostPagingAdapter()
            adapter = postAdapter.withLoadStateHeaderAndFooter(
                header = PostLoadStateAdapter { postAdapter.retry() },
                footer = PostLoadStateAdapter { postAdapter.retry() }
            )
        }
    }


}