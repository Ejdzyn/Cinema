package com.demo.springboot.dto;

import java.util.List;
import java.util.stream.Collectors;

public class MovieListDto {

    private int id = 0;

    private List<MovieDto> movies;

    public MovieListDto(List<MovieDto> movies) {
        this.movies = movies;
    }

    public List<MovieDto> getMovies() {
        return movies;
    }

    public void addMovie(MovieDto newMovie){
        this.movies.add(newMovie);
        incrementId();
    }

    public void delMovie(int id){
        movies.remove(id);
    }

    @Override
    public String toString() {
        return "[" + movies.stream()
                .map(movie -> movie.getMovieId().toString())
                .collect(Collectors.joining(",")) + "]";
    }

    public int getId() {
        return id;
    }

    public void incrementId() {
        this.id++;
    }
}
