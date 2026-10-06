package repository;

import java.util.ArrayList;
import model.Screen;
import model.Theatre;

public class TheatreRepository {
    private ArrayList<Theatre> theatres = new ArrayList<>();

    public void addTheatre(Theatre theatre) {
        theatres.add(theatre);
    }

    public ArrayList<Theatre> getAllTheatres() {
        return theatres;
    }

    public Theatre findById(int theatreId) {
        for (Theatre theatre : theatres) {
            if (theatre.getTheatreId() == theatreId) {
                return theatre;
            }
        }
        return null;
    }

    public boolean deleteTheatre(int theatreId) {
        Theatre theatre = findById(theatreId);
        if (theatre != null) {
            return theatres.remove(theatre);
        }
        return false;
    }

    public boolean addScreenToTheatre(int theatreId, Screen screen) {
        Theatre theatre = findById(theatreId);
        if (theatre != null) {
            theatre.addScreen(screen);
            return true;
        }
        return false;
    }

    public Screen findScreen(int theatreId, int screenId) {
        Theatre theatre = findById(theatreId);
        if (theatre != null) {
            return theatre.getScreenById(screenId);
        }
        return null;
    }
}
