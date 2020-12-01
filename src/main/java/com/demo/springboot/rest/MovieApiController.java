package com.demo.springboot.rest;

import com.demo.springboot.dto.CreateMovieDto;
import com.demo.springboot.dto.MovieDto;
import com.demo.springboot.dto.MovieListDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieApiController {
    private static final Logger LOG = LoggerFactory.getLogger(MovieApiController.class);

    private final MovieListDto movies;

    public MovieApiController() {
        List<MovieDto> moviesList = new ArrayList<>();
        moviesList.add(new MovieDto(1,
                "Piraci z Krzemowej Doliny",
                1999,
                "https://fwcdn.pl/fpo/30/02/33002/6988507.6.jpg")
        );
        movies = new MovieListDto(moviesList);
    }

    @GetMapping("/movies")
    public ResponseEntity<MovieListDto> getMovies() {
        LOG.info("--- get all movies: {}", movies);
        return ResponseEntity.ok().body(movies);    // = new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/movies/{id}/title/{title}")
    public ResponseEntity<Void> getMovie(@PathVariable("id") Integer id, @PathVariable("title") String title) {
        LOG.info("--- id: {}", id);
        LOG.info("--- title: {}", title);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/movies")
    public ResponseEntity<Void> updateMovie(@RequestParam("id") Integer id, @RequestParam("title") String title) {
        LOG.info("--- id: {}", id);
        LOG.info("--- title: {}", title);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/movies")
    public ResponseEntity<Void> createMovie(@RequestBody CreateMovieDto createMovieDto) throws URISyntaxException {
        LOG.info("--- id: {}", createMovieDto.getMovieId());
        LOG.info("--- title: {}", createMovieDto.getTitle());

        return ResponseEntity.created(new URI("/movies/" + createMovieDto.getMovieId())).build();
    }
}
