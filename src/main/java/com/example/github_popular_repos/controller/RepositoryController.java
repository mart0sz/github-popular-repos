package com.example.github_popular_repos.controller;

import com.example.github_popular_repos.model.GithubRepository;
import com.example.github_popular_repos.service.GithubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Repository API", description = "API do zarządzania popularnymi repozytoriami")
public class RepositoryController {

    private final GithubService githubService;

    public RepositoryController(GithubService githubService) {
        this.githubService = githubService;
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
}
