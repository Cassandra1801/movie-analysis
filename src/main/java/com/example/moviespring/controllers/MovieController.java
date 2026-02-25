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
        return new ModelAndView("index");
    }

    @GetMapping("/count")
    public ModelAndView count(){
        //Todo
        return null;
    }

    @GetMapping("/first")
    public ModelAndView getFirst(){
        ModelAndView mav = new ModelAndView();
        Movie first = service.getFirst();
        mav.addObject("firstMovie",first);
        return new ModelAndView("first");
    }
}
