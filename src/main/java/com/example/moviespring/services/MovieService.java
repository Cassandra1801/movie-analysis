package com.example.moviespring.services;

import com.example.moviespring.models.Movie;
import com.example.moviespring.repositories.MovieRepository;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private MovieRepository movieRepo = new MovieRepository();
    private List<Movie> allMovies = movieRepo.getAllMovies();

    public Movie getFirst(){
        return allMovies.get(1);
    }
}
