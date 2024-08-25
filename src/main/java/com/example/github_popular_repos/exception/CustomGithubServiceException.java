package com.example.github_popular_repos.exception;

public class CustomGithubServiceException extends RuntimeException {

    public CustomGithubServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
