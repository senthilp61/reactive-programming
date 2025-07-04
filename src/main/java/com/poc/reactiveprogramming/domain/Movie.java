package com.poc.reactiveprogramming.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private MovieInfo movieInfo;
    private List<Review> reviewList;
    private Revenue revenue;

}
