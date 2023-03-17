package com.assignment.githubrepos

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.githubrepos.adapter.ReposAdapter
import com.assignment.githubrepos.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObserver()
        val repoAdapter = ReposAdapter()
        val searchField : EditText = findViewById(R.id.edSearch)
        searchField.doOnTextChanged { text, _, _, _ -> viewModel.searchRepositories(text.toString())  }
        val recyclerView: RecyclerView = findViewById(R.id.rcv_Repos)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.goToNextPage()
                }
            }
        })

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = repoAdapter
        viewModel.repos.observe(this) {
            repoAdapter.submitList(it.items)
        }

    }

    private fun setupObserver() {

    }



}