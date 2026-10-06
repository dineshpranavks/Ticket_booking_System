package repository;

import java.util.ArrayList;
import model.Show;

public class ShowRepository {
    private ArrayList<Show> shows = new ArrayList<>();

    public void addShow(Show show) {
        shows.add(show);
    }

    public ArrayList<Show> getAllShows() {
        return shows;
    }

    public Show findById(int showId) {
        for (Show show : shows) {
            if (show.getShowId() == showId) {
                return show;
            }
        }
        return null;
    }

    public ArrayList<Show> getShowsByMovie(int movieId) {
        ArrayList<Show> result = new ArrayList<>();
        for (Show show : shows) {
            if (show.getMovieId() == movieId && !show.isCancelled()) {
                result.add(show);
            }
        }
        return result;
    }

    public ArrayList<Show> getShowsByTheatre(int theatreId) {
        ArrayList<Show> result = new ArrayList<>();
        for (Show show : shows) {
            if (show.getTheatreId() == theatreId && !show.isCancelled()) {
                result.add(show);
            }
        }
        return result;
    }

    public ArrayList<Show> getShowsByMovieAndTheatre(int movieId, int theatreId) {
        ArrayList<Show> result = new ArrayList<>();
        for (Show show : shows) {
            if (show.getMovieId() == movieId && show.getTheatreId() == theatreId && !show.isCancelled()) {
                result.add(show);
            }
        }
        return result;
    }

    public boolean deleteShow(int showId) {
        Show show = findById(showId);
        if (show != null) {
            return shows.remove(show);
        }
        return false;
    }
}
