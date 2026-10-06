package repository;

import java.util.ArrayList;
import model.Review;

public class ReviewRepository {
    private ArrayList<Review> reviews = new ArrayList<>();

    public void addReview(Review review) {
        reviews.add(review);
    }

    public ArrayList<Review> getReviewsByMovieId(int movieId) {
        ArrayList<Review> result = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getMovieId() == movieId) {
                result.add(review);
            }
        }
        return result;
    }

    public ArrayList<Review> getReviewsByUserId(int userId) {
        ArrayList<Review> result = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getUserId() == userId) {
                result.add(review);
            }
        }
        return result;
    }

    public boolean hasUserReviewed(int userId, int movieId) {
        for (Review review : reviews) {
            if (review.getUserId() == userId && review.getMovieId() == movieId) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Review> getAllReviews() {
        return reviews;
    }

    public double calculateAverageRating(int movieId) {
        ArrayList<Review> movieReviews = getReviewsByMovieId(movieId);
        if (movieReviews.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Review r : movieReviews) {
            sum += r.getRating();
        }
        return Math.round((sum / movieReviews.size()) * 10.0) / 10.0;
    }
}
