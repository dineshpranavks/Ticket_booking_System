package service;

import java.util.ArrayList;
import model.Screen;
import model.Show;
import model.Theatre;
import repository.ShowRepository;
import repository.TheatreRepository;

public class TheatreService {
    private TheatreRepository theatreRepository;
    private ShowRepository showRepository;

    public TheatreService(TheatreRepository theatreRepository, ShowRepository showRepository) {
        this.theatreRepository = theatreRepository;
        this.showRepository = showRepository;
    }

    public Theatre addTheatre(String name, String location, String city) {
        int theatreId = theatreRepository.getAllTheatres().size() + 1;
        Theatre theatre = new Theatre(theatreId, name, location, city);
        theatreRepository.addTheatre(theatre);
        return theatre;
    }

    public ArrayList<Theatre> getAllTheatres() {
        return theatreRepository.getAllTheatres();
    }

    public Theatre getTheatreById(int theatreId) {
        return theatreRepository.findById(theatreId);
    }

    public Screen addScreenToTheatre(int theatreId, String screenName, int totalRows, int seatsPerRow) {
        Theatre theatre = theatreRepository.findById(theatreId);
        if (theatre == null) return null;

        int screenId = theatre.getScreens().size() + 1;
        Screen screen = new Screen(screenId, theatreId, screenName, totalRows, seatsPerRow);
        theatre.addScreen(screen);
        return screen;
    }

    public Screen getScreen(int theatreId, int screenId) {
        return theatreRepository.findScreen(theatreId, screenId);
    }

    public Show addShow(int movieId, int theatreId, int screenId, String showDate, String showTime) {
        Theatre theatre = theatreRepository.findById(theatreId);
        if (theatre == null) return null;
        Screen screen = theatre.getScreenById(screenId);
        if (screen == null) return null;

        int showId = showRepository.getAllShows().size() + 1;
        Show show = new Show(showId, movieId, theatreId, screenId, showDate, showTime);
        showRepository.addShow(show);
        return show;
    }

    public ArrayList<Show> getShowsByMovie(int movieId) {
        return showRepository.getShowsByMovie(movieId);
    }

    public ArrayList<Show> getShowsByTheatre(int theatreId) {
        return showRepository.getShowsByTheatre(theatreId);
    }
    
    public ArrayList<Show> getShowsByMovieAndTheatre(int movieId, int theatreId) {
        return showRepository.getShowsByMovieAndTheatre(movieId, theatreId);
    }

    public Show getShowById(int showId) {
        return showRepository.findById(showId);
    }

    public boolean cancelShow(int showId) {
        Show show = showRepository.findById(showId);
        if (show == null) return false;
        show.setCancelled(true);
        return true;
    }
}
