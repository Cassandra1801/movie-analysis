package com.example.moviespring.controllers;

import com.example.moviespring.models.Movie;
import com.example.moviespring.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        ModelAndView mav = new ModelAndView();
        Movie first = service.getFirst();
        mav.addObject("firstMovie",first);
        return mav;
    }
}
