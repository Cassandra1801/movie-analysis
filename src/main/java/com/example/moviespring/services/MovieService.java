package com.example.moviespring.services;

import com.example.moviespring.models.Movie;
import com.example.moviespring.repositories.MovieRepository;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieService {
    private MovieRepository movieRepo = new MovieRepository();
    private List<Movie> allMovies = movieRepo.getAllMovies();

    public Movie getFirst(){
        return allMovies.get(0);
    }

    public int getCount(){
        return allMovies.size();
    }

    public double getAverageLength(){
        return getAverageLength(allMovies);
    }

    // Note: kept as static so it can also be reused in tests or with filtered movie lists.
    public static double getAverageLength(List<Movie> movies){
        int total = 0;

        for (Movie m : movies) {
            total += m.getLength();
        }
        return (double) total / movies.size();
    }

    // Note: age is calculated from the current year, so this value changes over time.
    public double getAverageAwardsAge() {
        int currentYear = Year.now().getValue();
        int totalAge = 0;
        int awardsMovieCount = 0;

        for (Movie movie : allMovies) {
            if (movie.isAwards()) {
                totalAge += currentYear - movie.getYear();
                awardsMovieCount++;
            }
        }

        if (awardsMovieCount == 0) {
            return 0;
        }

        return (double) totalAge / awardsMovieCount;
    }

    // Note: "most popular" in this exercise means most frequently occurring genre in the dataset.
    public String getMostPopularGenre() {
        Map<String, Integer> genreCount = getMovieCountPerGenre();
        String mostPopularGenre = "";
        int highestCount = 0;

        for (Map.Entry<String, Integer> entry : genreCount.entrySet()) {
            if (entry.getValue() > highestCount) {
                highestCount = entry.getValue();
                mostPopularGenre = entry.getKey();
            }
        }

        return mostPopularGenre;
    }

    // Note: HashMap is used to count occurrences per genre in O(n) time.
    public Map<String, Integer> getMovieCountPerGenre() {
        Map<String, Integer> genreCount = new HashMap<>();

        for (Movie movie : allMovies) {
            String genre = movie.getSubject();
            genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
        }

        return genreCount;
    }

    // Note: loops through every movie and counts exact year matches.
    public int getHowManyPerYear(int year) {
        int movieCountForYear = 0;

        for (Movie movie : allMovies) {
            if (movie.getYear() == year) {
                movieCountForYear++;
            }
        }

        return movieCountForYear;
    }
}
