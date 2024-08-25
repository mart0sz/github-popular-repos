package com.example.github_popular_repos.controller;

import com.example.github_popular_repos.model.GithubRepository;
import com.example.github_popular_repos.service.IGithubService;
import com.example.github_popular_repos.service.IFavouriteRepositoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@Tag(name = "Repository API", description = "API do zarządzania popularnymi repozytoriami")
public class RepositoryController {

    private final IGithubService githubService;
    private final IFavouriteRepositoryService favouriteRepositoryService;

    @Autowired
    public RepositoryController(IGithubService githubService, IFavouriteRepositoryService favouriteRepositoryService) {
        this.githubService = githubService;
        this.favouriteRepositoryService = favouriteRepositoryService;
    }

    @GetMapping("/api/repositories/popular")
    @Operation(summary = "Pobierz popularne repozytoria", description = "Zwraca listę najpopularniejszych repozytoriów na GitHubie")
    public List<GithubRepository> getPopularRepositories(
            @Parameter(description = "Data, od której repozytoria mają być utworzone")
            @RequestParam String date,
            @Parameter(description = "Język programowania repozytoriów")
            @RequestParam(required = false) String language,
            @Parameter(description = "Liczba zwracanych repozytoriów")
            @RequestParam(defaultValue = "10") int limit) {
        return githubService.getPopularRepositories(date, language, limit);
    }

    @PostMapping("/api/repositories/favourites")
    @Operation(summary = "Dodaj repozytorium do ulubionych", description = "Dodaje repozytorium do listy ulubionych")
    public void addToFavourites(@RequestBody GithubRepository repository) {
        favouriteRepositoryService.addToFavourites(repository);
    }

    @DeleteMapping("/api/repositories/favourites")
    @Operation(summary = "Usuń repozytorium z ulubionych", description = "Usuwa repozytorium z listy ulubionych")
    public void removeFromFavourites(@RequestBody GithubRepository repository) {
        favouriteRepositoryService.removeFromFavourites(repository);
    }

    @GetMapping("/api/repositories/favourites")
    @Operation(summary = "Pobierz ulubione repozytoria", description = "Zwraca listę ulubionych repozytoriów")
    public Set<GithubRepository> getAllFavourites() {
        return favouriteRepositoryService.getAllFavourites();
    }
}
