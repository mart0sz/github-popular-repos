package com.example.github_popular_repos.service.impl;

import com.example.github_popular_repos.model.GithubRepository;
import com.example.github_popular_repos.service.IFavouriteRepositoryService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FavouriteRepositoryServiceImpl implements IFavouriteRepositoryService {

    private final Set<GithubRepository> favourites = new HashSet<>();

    @Override
    public void addToFavourites(GithubRepository repository) {
        favourites.add(repository);
    }

    @Override
    public void removeFromFavourites(GithubRepository repository) {
        favourites.remove(repository);
    }

    @Override
    public Set<GithubRepository> getAllFavourites() {
        return new HashSet<>(favourites);
    }
}
