package com.example.github_popular_repos.service.impl;

import com.example.github_popular_repos.config.GithubConfig;
import com.example.github_popular_repos.exception.CustomGithubServiceException;
import com.example.github_popular_repos.model.GithubRepository;
import com.example.github_popular_repos.model.GithubResponse;
import com.example.github_popular_repos.service.IGithubService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Log4j2
public class GithubServiceImpl implements IGithubService {

    private static final String SEARCH_REPOSITORIES_PATH = "/search/repositories?q=created:>";
    private static final String LANGUAGE_PARAM = "+language:";
    private static final String SORT_PARAM = "&sort=stars&order=desc&per_page=";

    private final RestTemplate restTemplate;
    private final GithubConfig githubConfig;

    public GithubServiceImpl(RestTemplate restTemplate, GithubConfig githubConfig) {
        this.restTemplate = restTemplate;
        this.githubConfig = githubConfig;
    }

    @Override
    public List<GithubRepository> getPopularRepositories(String date, String language, int limit) {
        StringBuilder urlBuilder = new StringBuilder(githubConfig.getGithubApiUrl())
                .append(SEARCH_REPOSITORIES_PATH)
                .append(date);

        if (StringUtils.isNotBlank(language)) {
            urlBuilder.append(LANGUAGE_PARAM).append(language);
        }

        urlBuilder.append(SORT_PARAM).append(limit);

        String url = urlBuilder.toString();
        log.info("Request URL: " + url);

        try {
            GithubResponse response = restTemplate.getForObject(url, GithubResponse.class);
            return response != null ? response.getItems() : List.of();
        } catch (RuntimeException e) {
            log.error("Error while fetching popular repositories from GitHub", e);
            throw new CustomGithubServiceException("Failed to fetch repositories from GitHub", e);
        }
    }
}
