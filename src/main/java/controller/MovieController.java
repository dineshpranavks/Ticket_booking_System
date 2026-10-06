package controller;

import java.util.ArrayList;

import model.Movie;
import service.MovieService;

public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public Movie addMovie(String title, String genre, String language, int duration, String releaseDate, double rating, String status) {
        return movieService.addMovie(title, genre, language, duration, releaseDate, rating, status);
    }

    public Movie addMovie(String title, String genre, String language, int duration, String releaseDate, double rating) {
        return movieService.addMovie(title, genre, language, duration, releaseDate, rating);
    }

    public ArrayList<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    public Movie getMovieById(int movieId) {
        return movieService.getMovieById(movieId);
    }

    public boolean updateMovie(int movieId, String title, String genre, String language, int duration, String releaseDate, double rating, String status) {
        return movieService.updateMovie(movieId, title, genre, language, duration, releaseDate, rating, status);
    }

    public boolean updateMovie(int movieId, String title, String genre, String language, int duration, String releaseDate, double rating) {
        return movieService.updateMovie(movieId, title, genre, language, duration, releaseDate, rating);
    }

    public boolean deleteMovie(int movieId) {
        return movieService.deleteMovie(movieId);
    }

    public ArrayList<Movie> searchMovies(String title) {
        return movieService.searchMovies(title);
    }

    public ArrayList<Movie> filterByGenre(String genre) {
        return movieService.filterByGenre(genre);
    }

    public ArrayList<Movie> filterByLanguage(String language) {
        return movieService.filterByLanguage(language);
    }

    public ArrayList<Movie> filterByRating(double rating) {
        return movieService.filterByRating(rating);
    }

    public ArrayList<Movie> filterByStatus(String status) {
        return movieService.filterByStatus(status);
    }

    public ArrayList<Movie> getNowShowingMovies() {
        return movieService.getNowShowingMovies();
    }

    public ArrayList<Movie> getUpcomingMovies() {
        return movieService.getUpcomingMovies();
    }
}