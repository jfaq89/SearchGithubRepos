package com.assignment.githubrepos.api

import javax.inject.Inject

class Repository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun searchRepositories(searchTerm: String, page: Int) =
        apiHelper.searchRepositories(searchTerm, page)
}