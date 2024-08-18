package com.example.github_popular_repos.model;

import lombok.Data;

import java.util.List;

@Data
public class GithubResponse {
    private int totalCount;
    private boolean incompleteResults;
    private List<GithubRepository> items;
}
