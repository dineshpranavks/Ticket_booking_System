package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Review {
    private int reviewId;
    private int userId;
    private String userName;
    private int movieId;
    private String movieTitle;
    private double rating; // 1.0 to 5.0 (or 1 to 10)
    private String comment;
    private String reviewDate;

    public Review(int reviewId, int userId, String userName, int movieId, String movieTitle, double rating, String comment) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.userName = userName;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void displayReview() {
        System.out.println("--------------------------------------------------");
        System.out.printf("User: %-15s | Rating: %.1f/5.0 ⭐ | Date: %s\n", userName, rating, reviewDate);
        System.out.println("Review: " + comment);
        System.out.println("--------------------------------------------------");
    }
}
