package controller;

import java.util.ArrayList;

import model.Review;
import service.ReviewService;

public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public Review addReview(int userId, String userName, int movieId, double rating, String comment) {
        return reviewService.addReview(userId, userName, movieId, rating, comment);
    }

    public ArrayList<Review> getReviewsForMovie(int movieId) {
        return reviewService.getReviewsForMovie(movieId);
    }

    public ArrayList<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }
}
