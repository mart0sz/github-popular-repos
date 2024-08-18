package com.example.github_popular_repos.service;

import com.example.github_popular_repos.model.GithubRepository;
import com.example.github_popular_repos.model.GithubResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GithubService {

    private final RestTemplate restTemplate;

    @Value("${github.api.url}")
    private String githubApiUrl;

    public GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<GithubRepository> getPopularRepositories(String date, String language, int limit) {
        String url = this.githubApiUrl + "/search/repositories?q=created:>" + date;

        if (language != null && !language.isEmpty()) {
            url += "+language:" + language;
        }

        url += "&sort=stars&order=desc&per_page=" + limit;
        System.out.println("Request URL: " + url);
        GithubResponse response = restTemplate.getForObject(url, GithubResponse.class);

        return response != null ? response.getItems() : List.of();
    }
}
