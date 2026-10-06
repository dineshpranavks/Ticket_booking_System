package controller;

import java.util.ArrayList;

import model.Screen;
import model.Show;
import model.Theatre;
import service.TheatreService;

public class TheatreController {
    private TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    public Theatre addTheatre(String name, String location, String city) {
        return theatreService.addTheatre(name, location, city);
    }

    public ArrayList<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }

    public Theatre getTheatreById(int theatreId) {
        return theatreService.getTheatreById(theatreId);
    }

    public Screen addScreenToTheatre(int theatreId, String screenName, int totalRows, int seatsPerRow) {
        return theatreService.addScreenToTheatre(theatreId, screenName, totalRows, seatsPerRow);
    }

    public Show addShow(int movieId, int theatreId, int screenId, String showDate, String showTime) {
        return theatreService.addShow(movieId, theatreId, screenId, showDate, showTime);
    }

    public ArrayList<Show> getShowsByMovie(int movieId) {
        return theatreService.getShowsByMovie(movieId);
    }

    public Show getShowById(int showId) {
        return theatreService.getShowById(showId);
    }
}
