package com.demo.springboot.services;

import com.demo.springboot.dto.CreateMovieDto;
import com.demo.springboot.dto.MovieDto;
import com.demo.springboot.dto.MovieListDto;

import java.util.ArrayList;
import java.util.List;

public class CinemaService implements MovieServices {

    private MovieListDto movies;

    public CinemaService() {

        List<MovieDto> moviesList = new ArrayList<>();
        moviesList.add(new MovieDto(1,
                "Piraci z Krzemowej Doliny",
                1999,
                "https://fwcdn.pl/fpo/30/02/33002/6988507.6.jpg")
        );
        movies = new MovieListDto(moviesList);
        movies.incrementId();
    }

    @Override
    public MovieListDto getAllMovies() {
        return movies;
    }

    @Override
    public MovieDto getMovie(Integer id) {

        if(movies.getMovies().stream().anyMatch(movieDto -> movieDto.getMovieId().equals(id))){
            return movies.getMovies().stream().filter(movieDto -> movieDto.getMovieId().equals(id)).findFirst().get();
        } else {
            return null;
        }

        /*for(MovieDto movie : movies.getMovies()){
            if(movie.getMovieId().equals(id)){

            }
        }*/
    }

    @Override
    public Boolean updateMovie(Integer id, CreateMovieDto newMovie) {
        if(movies.getMovies().stream().findAny().filter(MovieDto -> MovieDto.getMovieId().equals(id)).isPresent()){
            //mozna uzyc filtra
            for(MovieDto movie : movies.getMovies()){
                if(movie.getMovieId().equals(id)){
                    movie.setImage(newMovie.getImage());
                    movie.setYear(newMovie.getYear());
                    movie.setTitle(newMovie.getTitle());
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void addMovie(CreateMovieDto newMovie) {
        movies.incrementId();
        movies.addMovie(new MovieDto(movies.getId(),newMovie.getTitle(),newMovie.getYear(),newMovie.getImage()));
    }

    @Override
    public Boolean delMovie(int id) {
        //movies.delMovie(id);
        for(MovieDto movie : movies.getMovies()){
            if(movie.getMovieId().equals(id)){
                movies.getMovies().remove(movie);
                return true;
            }
        }
        return false;
    }
}
