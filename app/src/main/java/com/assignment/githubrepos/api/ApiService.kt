package com.assignment.githubrepos.api

import com.assignment.githubrepos.model.GithubRepositories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") searchTerm: String,@Query("page") page: String): Response<GithubRepositories>

}