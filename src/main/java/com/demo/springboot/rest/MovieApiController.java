package com.demo.springboot.rest;

import com.demo.springboot.dto.CreateMovieDto;
import com.demo.springboot.dto.MovieDto;
import com.demo.springboot.dto.MovieListDto;
import com.demo.springboot.services.CinemaService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @GetMapping("/movies/{id}/title/{title}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable("id") Integer id, @PathVariable("title") String title) {
        LOG.info("--- id: {}", id);
        LOG.info("--- title: {}", title);

        MovieDto result = cinemaService.getMovie(id,title);

        if(result==null){
            LOG.info("NIE OK");
            return ResponseEntity.notFound().build();
        } else {
            LOG.info("OK");
            return ResponseEntity.ok().body(result);
        }
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> delMovie(@PathVariable("id") Integer id){

        cinemaService.delMovie(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable("id") Integer id, @RequestBody CreateMovieDto createMovieDto) {
        LOG.info("--- id: {}", id);
        //LOG.info("--- title: {}", title);

        cinemaService.updateMovie(id,createMovieDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/movies")
    public ResponseEntity<Void> createMovie(@RequestBody CreateMovieDto createMovieDto) throws URISyntaxException {
        //LOG.info("--- id: {}", createMovieDto.getMovieId());
        LOG.info("--- title: {}", createMovieDto.getTitle());

        cinemaService.addMovie(createMovieDto);

        return ResponseEntity.created(new URI("/movies/" + createMovieDto.getTitle())).build();
    }
}
