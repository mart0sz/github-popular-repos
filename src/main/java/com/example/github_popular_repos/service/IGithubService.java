package com.example.github_popular_repos.service;

import com.example.github_popular_repos.model.GithubRepository;

import java.util.List;

public interface IGithubService {
   public List<GithubRepository> getPopularRepositories(String date, String language, int limit);
}
