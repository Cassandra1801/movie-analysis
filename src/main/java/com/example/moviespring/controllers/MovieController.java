package com.example.moviespring.controllers;

import com.example.moviespring.models.Movie;
import com.example.moviespring.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class MovieController {
    private MovieService service = new MovieService();

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("name","Nicklas");
        return mav;
    }

    //Return how many movies are in the data file
    @GetMapping("/count")
    public ModelAndView count(){
        ModelAndView mav = new ModelAndView("examples/count");
        int count = service.getCount();
        mav.addObject("count", count);
        return mav;
    }

    @GetMapping("/first")
    public ModelAndView getFirst(){
        ModelAndView mav = new ModelAndView("first");
        Movie first = service.getFirst();
        mav.addObject("firstMovie",first);
        return mav;
    }

    @GetMapping("/firstAndLast")
    public ModelAndView getFirstAndLast() {
        ModelAndView mav = new ModelAndView("examples/first-and-last");
        mav.addObject("firstMovie", service.getFirst());
        mav.addObject("lastMovie", service.getLast());
        return mav;
    }

    @GetMapping("/adventure")
    public ModelAndView getAdventureMovies() {
        ModelAndView mav = new ModelAndView("examples/adventure");
        List<Movie> adventureMovies = service.getAdventureMovies();
        mav.addObject("movies", adventureMovies);
        mav.addObject("averageLength", service.getAverageAdventureLength());
        return mav;
    }

    @GetMapping("/first-10-award")
    public ModelAndView getFirstTenAwardMovies() {
        ModelAndView mav = new ModelAndView("examples/first-10-award");
        mav.addObject("movies", service.getFirstTenAwardMovies());
        return mav;
    }

    // Note: returns a plain number in minutes (not a rendered HTML view).
    @GetMapping("/averageLength")
    @ResponseBody
    public double getAverageLength() {
        return service.getAverageLength();
    }

    // Note: returns average age in years for movies where awards == true.
    @GetMapping("/averageAwardsAge")
    @ResponseBody
    public double getAverageAwardsAge() {
        return service.getAverageAwardsAge();
    }

    // Note: returns the genre that appears the most in the dataset.
    @GetMapping("/mostPopular")
    @ResponseBody
    public String getMostPopularGenre() {
        return service.getMostPopularGenre();
    }

    // Note: returns a key/value map with genre -> number of movies.
    @GetMapping("/howManyPerGenre")
    @ResponseBody
    public Map<String, Integer> getMovieCountPerGenre() {
        return service.getMovieCountPerGenre();
    }

    // Note: preferred endpoint usage: /howManyPerYear?year=1992
    @GetMapping("/howManyPerYear")
    public ModelAndView getHowManyPerYearByQueryParam(@RequestParam("year") int year) {
        return buildHowManyPerYearView(year);
    }

    // Supports the exercise URL format: /howManyPerYear=1992
    // Note: compatibility endpoint for the exercise format: /howManyPerYear=1992
    @GetMapping("/howManyPerYear={year}")
    public ModelAndView getHowManyPerYearByPath(@PathVariable int year) {
        return buildHowManyPerYearView(year);
    }

    // Note: returns a normal page for hits and a 404 page message for misses.
    private ModelAndView buildHowManyPerYearView(int year) {
        ModelAndView mav = new ModelAndView("examples/how-many-per-year");
        int movieCountForYear = service.getHowManyPerYear(year);
        mav.addObject("year", year);

        if (movieCountForYear == 0) {
            mav.addObject("message", "404: No movie was produced that year");
            mav.setStatus(HttpStatus.NOT_FOUND);
            return mav;
        }

        mav.addObject("message", "Movies produced in " + year + ": " + movieCountForYear);
        return mav;
    }
}
