package com.example.githubsearch;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GithubRepository {
    Integer id;
    String name;
    String description;
}
