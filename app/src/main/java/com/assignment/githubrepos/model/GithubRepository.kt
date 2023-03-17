package com.assignment.githubrepos.model

class GithubRepositories(val items: List<GithubRepository>)
class GithubRepository(
    val name: String,
    val owner: GitHubUser,
    val html_url: String,
    val description: String
)

class GitHubUser(val avatar_url: String, val login: String)