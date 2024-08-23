package com.example.github_popular_repos.service;

import com.example.github_popular_repos.model.GithubRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FavouriteRepositoryService {

    // Używamy HashSet do przechowywania ulubionych repozytoriów
    private final Set<GithubRepository> favourites = new HashSet<>();

    // Dodawanie repozytoriów do ulubionych
    public void addToFavourites(GithubRepository repository) {
        favourites.add(repository);
    }

    // Usuwanie repozytoriów z ulubionych
    public void removeFromFavourites(GithubRepository repository) {
        favourites.remove(repository);
    }

    // Pobieranie wszystkich ulubionych repozytoriów
    public Set<GithubRepository> getAllFavourites() {
        return new HashSet<>(favourites);
    }
}
