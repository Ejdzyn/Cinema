package com.demo.springboot.services;

import com.demo.springboot.dto.CreateMovieDto;
import com.demo.springboot.dto.MovieDto;
import com.demo.springboot.dto.MovieListDto;

public interface MovieServices {

    MovieListDto getAllMovies();

    MovieDto getMovie(Integer id, String title);

    void updateMovie(Integer id, CreateMovieDto newMovie);

    void addMovie(CreateMovieDto newMovie);

    void delMovie(int id);

}
