package com.example.github_popular_repos.model;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Owner {
    private String login;
    private Long id;
    private String avatarUrl;
    private String htmlUrl;
}