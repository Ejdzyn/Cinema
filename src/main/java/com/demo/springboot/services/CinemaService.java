package com.demo.springboot.services;

import com.demo.springboot.dto.CreateMovieDto;
import com.demo.springboot.dto.MovieDto;
import com.demo.springboot.dto.MovieListDto;

import java.util.ArrayList;
import java.util.List;

public class CinemaService implements MovieServices {

    private final MovieListDto movies;

    public CinemaService() {

        List<MovieDto> moviesList = new ArrayList<>();
        moviesList.add(new MovieDto(1,
                "Piraci z Krzemowej Doliny",
                1999,
                "https://fwcdn.pl/fpo/30/02/33002/6988507.6.jpg")
        );

        movies = new MovieListDto(moviesList);
    }

    @Override
    public MovieListDto getAllMovies() {
        return movies;
    }

    @Override
    public MovieDto getMovie(Integer id,String title) {

        if(movies.getMovies().stream().anyMatch(movieDto -> movieDto.getMovieId().equals(id) && movieDto.getTitle().equals(title))){
            return movies.getMovies().stream().filter(movieDto -> movieDto.getMovieId().equals(id) && movieDto.getTitle().equals(title)).findFirst().get();
        } else {
            return null;
        }
    }

    @Override
    public void updateMovie(Integer id, CreateMovieDto newMovie) {
        //movies.getMovies().set(id,new MovieDto());

        if(movies.getMovies().stream().findAny().filter(MovieDto -> MovieDto.getMovieId().equals(id)).isPresent()){
            //mozna uzyc filtra
            for(MovieDto movie : movies.getMovies()){
                if(movie.getMovieId().equals(id)){
                    movie.setImage(newMovie.getImage());
                    movie.setYear(newMovie.getYear());
                    movie.setTitle(newMovie.getTitle());
                    break;
                }
            }
        }
    }

    @Override
    public void addMovie(CreateMovieDto newMovie) {
        movies.addMovie(new MovieDto(movies.getId(),newMovie.getTitle(),newMovie.getYear(),newMovie.getImage()));
    }

    @Override
    public void delMovie(int id) {
        movies.delMovie(id);
    }
}
