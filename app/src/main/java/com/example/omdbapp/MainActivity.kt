package com.example.omdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.omdbapp.adapter.MyRecyclerViewAdapter
import com.example.omdbapp.databinding.ActivityMainBinding
import com.example.omdbapp.repository.model.Movie
import com.example.omdbapp.repository.model.MovieList
import com.example.omdbapp.viewModel.AppViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AppViewModel
    private var totalCount: Int = 0
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var myAdapter: MyRecyclerViewAdapter
    private var isLoading: Boolean = true
    private var PAGE: Int = 1
    private var movieCurrentList = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        viewModel = ViewModelProvider(this)[AppViewModel::class.java]

        binding.btnSearch.setOnClickListener {
            if (binding.tvSearch.editText?.text.toString().isNotEmpty()) {
                viewModel.getMovie(binding.tvSearch.editText?.text.toString(), PAGE)
                    .observe(this, Observer {
                        movieCurrentList.clear()
                        PAGE = 1
                        totalCount = it.totalResult.toInt()
                        if (it.response == "True") movieCurrentList.addAll(it.search)
                        val myAdapter = MyRecyclerViewAdapter(movieCurrentList, this)
                        binding.recyclerView.adapter = myAdapter
                        myAdapter.notifyDataSetChanged()
                    })
            }else{
                Toast.makeText(this@MainActivity, "Enter search keywords...", Toast.LENGTH_SHORT).show()
            }
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                        if (!binding.recyclerView.canScrollVertically(1)){
                            if (movieCurrentList.size <= totalCount){
                                PAGE++
                                viewModel.getMovie(binding.tvSearch.editText?.text.toString(), PAGE).observe(this@MainActivity, Observer {
                                    if (it.response == "True") movieCurrentList.addAll(it.search)
                                    val myAdapter = MyRecyclerViewAdapter(movieCurrentList, this@MainActivity)
                                    binding.recyclerView.adapter = myAdapter
                                    myAdapter.notifyDataSetChanged()
                                })
                            }
                        }
                }
        })
    }
}