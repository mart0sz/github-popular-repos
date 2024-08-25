package com.example.github_popular_repos.controller;

import com.example.github_popular_repos.model.GithubRepository;
import com.example.github_popular_repos.model.Owner;
import com.example.github_popular_repos.service.IGithubService;
import com.example.github_popular_repos.service.IFavouriteRepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RepositoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IGithubService githubService;

    @Mock
    private IFavouriteRepositoryService favouriteRepositoryService;

    @InjectMocks
    private RepositoryController repositoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(repositoryController).build();
    }

    @Test
    public void testGetPopularRepositories() throws Exception {
        Owner owner = Owner.builder()
                .login("ownerLogin")
                .id(1L)
                .avatarUrl("https://avatar.url")
                .htmlUrl("https://owner.url")
                .build();

        GithubRepository repo = GithubRepository.builder()
                .id(1L)
                .name("Repo1")
                .description("Description of Repo1")
                .owner(owner)
                .htmlUrl("https://repo1.url")
                .language("Java")
                .stargazersCount(100)
                .createdAt("2024-01-01T00:00:00Z")
                .build();

        List<GithubRepository> repositories = List.of(repo);

        when(githubService.getPopularRepositories(anyString(), anyString(), anyInt())).thenReturn(repositories);

        mockMvc.perform(get("/api/repositories/popular")
                        .param("date", "2024-01-01")
                        .param("language", "Java")
                        .param("limit", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].name").value("Repo1"))
                .andExpect(jsonPath("$[0].description").value("Description of Repo1"))
                .andExpect(jsonPath("$[0].htmlUrl").value("https://repo1.url"))
                .andExpect(jsonPath("$[0].language").value("Java"))
                .andExpect(jsonPath("$[0].stargazersCount").value(100))
                .andExpect(jsonPath("$[0].owner.login").value("ownerLogin"))
                .andExpect(jsonPath("$[0].owner.id").value(1))
                .andExpect(jsonPath("$[0].owner.avatarUrl").value("https://avatar.url"))
                .andExpect(jsonPath("$[0].owner.htmlUrl").value("https://owner.url"));

        verify(githubService).getPopularRepositories(anyString(), anyString(), anyInt());
    }
}
