package com.poc.reactiveprogramming.service;

import com.poc.reactiveprogramming.domain.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ReviewService {

    List<Review> retrieveReviews(long movieInfoId);
    Flux<Review> retireveReviewsFlux(long movieInfoId);
}
