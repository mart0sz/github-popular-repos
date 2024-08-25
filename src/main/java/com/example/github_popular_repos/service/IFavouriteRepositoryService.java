package com.example.github_popular_repos.service;

import com.example.github_popular_repos.model.GithubRepository;
import java.util.Set;

public interface IFavouriteRepositoryService {

    void addToFavourites(GithubRepository repository);

    void removeFromFavourites(GithubRepository repository);

    Set<GithubRepository> getAllFavourites();
}
