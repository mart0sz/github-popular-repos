package com.example.github_popular_repos.service.impl;

import com.example.github_popular_repos.config.GithubConfig;
import com.example.github_popular_repos.exception.CustomGithubServiceException;
import com.example.github_popular_repos.model.GithubRepository;
import com.example.github_popular_repos.model.GithubResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GithubServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private GithubConfig githubConfig;

    @InjectMocks
    private GithubServiceImpl githubService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPopularRepositories() {
        String date = "2024-01-01";
        String language = "Java";
        int limit = 10;

        GithubRepository repo = GithubRepository.builder()
                .name("Repo1")
                .description("Description of Repo1")
                .htmlUrl("https://repo1.url")
                .build();

        GithubResponse mockResponse = new GithubResponse();
        mockResponse.setItems(List.of(repo));

        when(githubConfig.getGithubApiUrl()).thenReturn("https://api.github.com");
        when(restTemplate.getForObject(anyString(), eq(GithubResponse.class))).thenReturn(mockResponse);

        List<GithubRepository> repositories = githubService.getPopularRepositories(date, language, limit);

        assertNotNull(repositories, "The repository list should not be null");
        assertFalse(repositories.isEmpty(), "The repository list should not be empty");
        assertEquals(1, repositories.size(), "The repository list size should be 1");
        assertEquals("Repo1", repositories.get(0).getName(), "The repository name should be 'Repo1'");
        assertEquals("Description of Repo1", repositories.get(0).getDescription(), "The repository description should be 'Description of Repo1'");
        assertEquals("https://repo1.url", repositories.get(0).getHtmlUrl(), "The repository URL should be 'https://repo1.url'");

        verify(restTemplate).getForObject(anyString(), eq(GithubResponse.class));
    }

    @Test
    public void testGetPopularRepositoriesHandlesException() {
        String date = "2024-01-01";
        String language = "Java";
        int limit = 10;

        when(githubConfig.getGithubApiUrl()).thenReturn("https://api.github.com");
        when(restTemplate.getForObject(anyString(), eq(GithubResponse.class))).thenThrow(new RuntimeException());

        assertThrows(CustomGithubServiceException.class, () -> {
            githubService.getPopularRepositories(date, language, limit);
        });

        verify(restTemplate).getForObject(anyString(), eq(GithubResponse.class));
    }
}
