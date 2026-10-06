package service;

import java.util.ArrayList;
import model.Movie;
import repository.MovieRepository;

public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(String title, String genre, String language, int duration, String releaseDate, double rating, String status) {
        int movieId = movieRepository.getAllMovies().size() + 1;
        Movie movie = new Movie(movieId, title, genre, language, duration, releaseDate, rating, status);
        movieRepository.addMovie(movie);
        return movie;
    }

    // Overloaded for backward compatibility
    public Movie addMovie(String title, String genre, String language, int duration, String releaseDate, double rating) {
        return addMovie(title, genre, language, duration, releaseDate, rating, "NOW_SHOWING");
    }

    public ArrayList<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public Movie getMovieById(int movieId) {
        return movieRepository.findById(movieId);
    }

    public boolean updateMovie(int movieId, String title, String genre, String language, int duration, String releaseDate, double rating, String status) {
        Movie movie = movieRepository.findById(movieId);
        if (movie == null)
            return false;

        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setLanguage(language);
        movie.setDuration(duration);
        movie.setReleaseDate(releaseDate);
        movie.setRating(rating);
        movie.setStatus(status);

        return true;
    }

    // Overloaded
    public boolean updateMovie(int movieId, String title, String genre, String language, int duration, String releaseDate, double rating) {
        return updateMovie(movieId, title, genre, language, duration, releaseDate, rating, "NOW_SHOWING");
    }

    public boolean deleteMovie(int movieId) {
        Movie movie = movieRepository.findById(movieId);
        if (movie == null)
            return false;
        movieRepository.deleteMovie(movie);
        return true;
    }

    public ArrayList<Movie> searchMovies(String title) {
        return movieRepository.searchByTitle(title);
    }

    public ArrayList<Movie> filterByGenre(String genre) {
        return movieRepository.filterByGenre(genre);
    }

    public ArrayList<Movie> filterByLanguage(String language) {
        return movieRepository.filterByLanguage(language);
    }

    public ArrayList<Movie> filterByRating(double rating) {
        return movieRepository.filterByRating(rating);
    }

    public ArrayList<Movie> filterByStatus(String status) {
        return movieRepository.filterByStatus(status);
    }

    public ArrayList<Movie> getNowShowingMovies() {
        return movieRepository.getNowShowingMovies();
    }

    public ArrayList<Movie> getUpcomingMovies() {
        return movieRepository.getUpcomingMovies();
    }
}