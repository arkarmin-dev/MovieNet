package cgm.system.MovieNet.service;

import cgm.system.MovieNet.entity.Review;

public interface ReviewService {
    void saveReview(Review review, String username);
}
