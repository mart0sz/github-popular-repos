package com.example.github_popular_repos.model;
import lombok.Data;

@Data
public class GithubRepository {
    private Long id;
    private String name;
    private String fullName;
    private Owner owner;
    private String htmlUrl;
    private String description;
    private int stargazersCount;
    private String language;
    private String createdAt;
}
