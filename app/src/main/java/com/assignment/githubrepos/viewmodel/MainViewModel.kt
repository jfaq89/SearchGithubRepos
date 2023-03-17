package com.assignment.githubrepos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.githubrepos.api.Repository
import com.assignment.githubrepos.model.GithubRepositories
import com.assignment.githubrepos.model.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: Repository,
) : ViewModel() {

    val repos = MutableLiveData<GithubRepositories>()
    private var currentPage = 0
    private var searchTerm = ""

    fun goToNextPage() {
        currentPage++
        loadData()
    }

    fun searchRepositories(term: String) {
        currentPage = 0
        searchTerm = term
        loadData()
    }

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            repo.searchRepositories(searchTerm, currentPage).body()?.let {
                if (currentPage == 0) {
                    repos.postValue(it)
                } else {
                    val list: List<GithubRepository> =
                        if (repos.value != null) repos.value!!.items else emptyList()
                    repos.postValue(GithubRepositories(list + it.items))
                }
            }
        }
    }
}