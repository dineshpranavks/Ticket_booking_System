package service;

import java.util.ArrayList;

import model.Booking;
import model.Movie;
import model.Review;
import repository.BookingRepository;
import repository.MovieRepository;
import repository.ReviewRepository;

public class ReviewService {
    private ReviewRepository reviewRepository;
    private BookingRepository bookingRepository;
    private MovieRepository movieRepository;

    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
        this.movieRepository = movieRepository;
    }

    public Review addReview(int userId, String userName, int movieId, double rating, String comment) {
        if (rating < 1.0 || rating > 10.0) return null;

        // Verify user booked the movie
        boolean hasBooked = false;
        for (Booking booking : bookingRepository.getBookingsByUserId(userId)) {
            if (booking.isConfirmed() && booking.getMovieTitle().equalsIgnoreCase(movieRepository.findById(movieId).getTitle())) {
                hasBooked = true;
                break;
            }
        }

        if (!hasBooked) return null;

        if (reviewRepository.hasUserReviewed(userId, movieId)) return null;

        Movie movie = movieRepository.findById(movieId);
        if (movie == null) return null;

        int reviewId = reviewRepository.getAllReviews().size() + 1;
        Review review = new Review(reviewId, userId, userName, movieId, movie.getTitle(), rating, comment);
        reviewRepository.addReview(review);

        // Update movie rating
        double newAvg = reviewRepository.calculateAverageRating(movieId);
        movieRepository.updateRating(movieId, newAvg);

        return review;
    }

    public ArrayList<Review> getReviewsForMovie(int movieId) {
        return reviewRepository.getReviewsByMovieId(movieId);
    }

    public ArrayList<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }
}
