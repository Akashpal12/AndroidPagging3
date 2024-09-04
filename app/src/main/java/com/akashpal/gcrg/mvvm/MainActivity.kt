package com.akashpal.gcrg.mvvm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akashpal.gcrg.mvvm.adapter.PostAdapter
import com.akashpal.gcrg.mvvm.api.RetrofitClient
import com.akashpal.gcrg.mvvm.api.RetrofitClientCart
import com.akashpal.gcrg.mvvm.databinding.ActivityMainBinding
import com.akashpal.gcrg.mvvm.models.Request
import com.akashpal.gcrg.mvvm.models.RequestContainer
import com.akashpal.gcrg.mvvm.models.RequestData
import com.akashpal.gcrg.mvvm.repository.CartRepository
import com.akashpal.gcrg.mvvm.repository.PostBTokenRepository
import com.akashpal.gcrg.mvvm.repository.PostRepository
import com.akashpal.gcrg.mvvm.viewModel.CartViewModel
import com.akashpal.gcrg.mvvm.viewModel.CartViewModelFactory
import com.akashpal.gcrg.mvvm.viewModel.PostBTokenViewModel
import com.akashpal.gcrg.mvvm.viewModel.PostBTokenViewModelFactory
import com.akashpal.gcrg.mvvm.viewModel.PostViewModel
import com.akashpal.gcrg.mvvm.viewModel.PostViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter


    private lateinit var cartViewModel: CartViewModel
    private lateinit var postBViewModel: PostBTokenViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val postService=RetrofitClient.getClient()
        val repository=PostRepository(postService)

        setupRecyclerView()

        mainViewModel= ViewModelProvider(this,PostViewModelFactory(repository)).get(PostViewModel::class.java)
        mainViewModel.posts.observe(this, Observer {
            if (it != null) {
                Log.d("PostData", it.toString())
                postAdapter.submitList(it)
            }
        })
        mainViewModel.getPostById(2).observe(this, Observer {
            if (it != null) {
                Log.d("SpecificPost", "Post with ID 2: ${it.title}")
            } else {
                Log.d("SpecificPost", "No post found with ID $2")
            }
        })

        val spinner: Spinner = findViewById(R.id.spinner_posts)
        mainViewModel.getPostTitles().observe(this) { titles ->
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, titles)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Get the selected item
                val selectedItem = parent.getItemAtPosition(position) as String

                // Show a Toast with the selected index and value
                Toast.makeText(
                    this@MainActivity, "Selected index: $position, Value: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle the case when nothing is selected if needed
            }
        }

        binding.btn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }





        // Initialize Retrofit and Repository
        val postCartService = RetrofitClientCart.getClient()
        val postCartRepository = CartRepository(postCartService)

        // Initialize ViewModel
        cartViewModel = ViewModelProvider(this,
            CartViewModelFactory(postCartRepository)).get(CartViewModel::class.java)


        // Observe the LiveData from ViewModel
        cartViewModel.cartResponse.observe(this, Observer { response ->
            if (response != null) {
                Log.d("CartResponse", "Success: ${response.d.responseMessage}")
                // Update your UI with the response data
            } else {
                Log.d("CartResponse", "Error fetching data")
            }
        })

        // Trigger the API call
        val request = Request(
            requestContainer = RequestContainer(
                securityToken = "",
                deviceId = "Andro8.5",
                latLong = "",
                appVersion = "yourAppVersion",
                requestSource = "kiosk"
            ),
            requestData = RequestData(
                CartAutoId = 22643
            )
        )

        cartViewModel.fetchCartDetails(request)




        // Initialize Retrofit and Repository
        val postBTokenService = RetrofitClientCart.getClient()
        val postBTokenRepository = PostBTokenRepository(postBTokenService)
        postBViewModel=ViewModelProvider(
            this,PostBTokenViewModelFactory(postBTokenRepository)).get(PostBTokenViewModel::class.java)


        postBViewModel.cartResponse.observe(this, Observer { response ->
            if (response != null) {
                Log.d("CartResponse", "Success: ${response.d.responseMessage}")
                // Update your UI with the response data
            } else {
                Log.d("CartResponse", "Error fetching data")
            }
        })

        // Trigger the API call
        val request1 = Request(
            requestContainer = RequestContainer(
                securityToken = "",
                deviceId = "Andro8.5",
                latLong = "",
                appVersion = "yourAppVersion",
                requestSource = "kiosk"
            ),
            requestData = RequestData(
                CartAutoId = 22643
            )
        )

        postBViewModel.fetchCartDetails(request,"AKajgyugvbftyf")
    }


    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        postAdapter = PostAdapter()
        recyclerView.adapter = postAdapter
    }


}