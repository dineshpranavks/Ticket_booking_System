package repository;

import java.util.ArrayList;
import model.Movie;

public class MovieRepository {
    private ArrayList<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public ArrayList<Movie> getAllMovies() {
        return movies;
    }

    public Movie findById(int movieId) {
        for (Movie movie : movies) {
            if (movie.getMovieId() == movieId)
                return movie;
        }
        return null;
    }

    public void deleteMovie(Movie movie) {
        movies.remove(movie);
    }

    public ArrayList<Movie> searchByTitle(String title) {
        ArrayList<Movie> result = new ArrayList<>();
        if (title == null) return result;
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase().trim()))
                result.add(movie);
        }
        return result;
    }

    public ArrayList<Movie> filterByGenre(String genre) {
        ArrayList<Movie> result = new ArrayList<>();
        if (genre == null) return result;
        for (Movie movie : movies) {
            if (movie.getGenre().toLowerCase().contains(genre.toLowerCase().trim()))
                result.add(movie);
        }
        return result;
    }

    public ArrayList<Movie> filterByLanguage(String language) {
        ArrayList<Movie> result = new ArrayList<>();
        if (language == null) return result;
        for (Movie movie : movies) {
            if (movie.getLanguage().toLowerCase().contains(language.toLowerCase().trim()))
                result.add(movie);
        }
        return result;
    }

    public ArrayList<Movie> filterByRating(double rating) {
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getRating() >= rating) {
                result.add(movie);
            }
        }
        return result;
    }

    public ArrayList<Movie> filterByStatus(String status) {
        ArrayList<Movie> result = new ArrayList<>();
        if (status == null) return result;
        for (Movie movie : movies) {
            if (movie.getStatus() != null && movie.getStatus().equalsIgnoreCase(status.trim())) {
                result.add(movie);
            }
        }
        return result;
    }

    public ArrayList<Movie> getNowShowingMovies() {
        return filterByStatus("NOW_SHOWING");
    }

    public ArrayList<Movie> getUpcomingMovies() {
        return filterByStatus("UPCOMING");
    }

    public void updateRating(int movieId, double newRating) {
        Movie movie = findById(movieId);
        if (movie != null) {
            movie.setRating(newRating);
        }
    }
}