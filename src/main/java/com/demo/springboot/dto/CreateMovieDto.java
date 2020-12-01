package com.demo.springboot.dto;

public class CreateMovieDto {
    private Integer movieId;
    private String title;

    public CreateMovieDto() {
    }

    public Integer getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }
}
