package com.poc.reactiveprogramming.service;

import com.poc.reactiveprogramming.domain.Review;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {


    @Override
    public List<Review> retrieveReviews(long movieInfoId) {

        return List.of(new Review(1l, movieInfoId, "Awesome Movie", 8.9),
                new Review(2l, movieInfoId, "Excellent Movie", 9.0));
    }

    @Override
    public Flux<Review> retireveReviewsFlux(long movieInfoId) {

        var reviewsList = List.of(new Review(1l, movieInfoId, "Awesome Movie", 8.9),
                new Review(2l, movieInfoId, "Excellent Movie", 9.0));
        return Flux.fromIterable(reviewsList);
    }
}
