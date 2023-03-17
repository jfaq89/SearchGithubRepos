package com.assignment.githubrepos.api

import com.assignment.githubrepos.model.GithubRepositories
import retrofit2.Response

interface ApiHelper {
    suspend fun searchRepositories(searchTerm: String,page: Int) : Response<GithubRepositories>
}