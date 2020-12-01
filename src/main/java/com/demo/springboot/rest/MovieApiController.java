package com.demo.springboot.rest;

import com.demo.springboot.dto.CreateMovieDto;
import com.demo.springboot.dto.MovieDto;
import com.demo.springboot.dto.MovieListDto;
import com.demo.springboot.services.CinemaService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MovieApiController {
    private static final Logger LOG = LoggerFactory.getLogger(MovieApiController.class);

    private CinemaService cinemaService;

    public MovieApiController() {

        this.cinemaService= new CinemaService();

    }

    @GetMapping("/movies")
    public ResponseEntity<MovieListDto> getMovies() {
        LOG.info("--- get all movies: {}", cinemaService.getAllMovies());
        return ResponseEntity.ok().body(cinemaService.getAllMovies());    // = new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable("id") Integer id) {
        LOG.info("--- id: {}", id);

        MovieDto result = cinemaService.getMovie(id);

        if(result==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> delMovie(@PathVariable("id") Integer id){

        if(cinemaService.delMovie(id)){
            return ResponseEntity.ok("DELETED");
        } else {
            return new ResponseEntity<>("MOVIE NOT FOUND", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable("id") Integer id, @RequestBody CreateMovieDto createMovieDto) {
        LOG.info("--- id: {}", id);
        //LOG.info("--- title: {}", title);

        if(cinemaService.updateMovie(id,createMovieDto)){
            return ResponseEntity.ok().build();
        } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/movies")
    public ResponseEntity<Void> createMovie(@RequestBody CreateMovieDto createMovieDto) throws URISyntaxException {
        createMovieDto.setTitle(createMovieDto.getTitle());
        LOG.info("--- title: {}", createMovieDto.getTitle());

        cinemaService.addMovie(createMovieDto);
        return ResponseEntity.ok().build();
        //return ResponseEntity.created(new URI("/movies/" + createMovieDto.getTitle())).build(); //-- illegal character, bo zamienia spacje na %20 a w uri # i % sa znakami wyjatkowymi czy jakos tak
    }
}
