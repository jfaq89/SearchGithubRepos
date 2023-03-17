package com.assignment.githubrepos.api

import com.assignment.githubrepos.model.GithubRepositories
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun searchRepositories(searchTerm: String, page:Int): Response<GithubRepositories>  = apiService.searchRepositories(searchTerm,page.toString())
}