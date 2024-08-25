package com.example.github_popular_repos.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class GithubConfig {

    @Value("${github.api.url}")
    private String githubApiUrl;
}
