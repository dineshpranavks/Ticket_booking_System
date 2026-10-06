package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Show {
    private int showId;
    private int movieId;
    private int theatreId;
    private int screenId;
    private String showDate; // Format: YYYY-MM-DD
    private String showTime; // Format: HH:MM AM/PM
    private double basePriceMultiplier; // Default 1.0
    private Set<String> bookedSeats;
    private boolean isCancelled;

    public Show(int showId, int movieId, int theatreId, int screenId, String showDate, String showTime) {
        this(showId, movieId, theatreId, screenId, showDate, showTime, 1.0);
    }

    public Show(int showId, int movieId, int theatreId, int screenId, String showDate, String showTime, double basePriceMultiplier) {
        this.showId = showId;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.screenId = screenId;
        this.showDate = showDate;
        this.showTime = showTime;
        this.basePriceMultiplier = basePriceMultiplier;
        this.bookedSeats = new HashSet<>();
        this.isCancelled = false;
    }

    public int getShowId() {
        return showId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public int getScreenId() {
        return screenId;
    }

    public String getShowDate() {
        return showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public double getBasePriceMultiplier() {
        return basePriceMultiplier;
    }

    public Set<String> getBookedSeats() {
        return bookedSeats;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public boolean isSeatBooked(String seatCode) {
        if (seatCode == null) return false;
        return bookedSeats.contains(seatCode.trim().toUpperCase());
    }

    public synchronized boolean bookSeats(List<String> seatsToBook) {
        if (seatsToBook == null || seatsToBook.isEmpty()) return false;
        // Verify none are already booked
        for (String seat : seatsToBook) {
            String formatted = seat.trim().toUpperCase();
            if (bookedSeats.contains(formatted)) {
                return false; // Conflict!
            }
        }
        // Book all
        for (String seat : seatsToBook) {
            bookedSeats.add(seat.trim().toUpperCase());
        }
        return true;
    }

    public synchronized void cancelSeats(List<String> seatsToCancel) {
        if (seatsToCancel == null) return;
        for (String seat : seatsToCancel) {
            bookedSeats.remove(seat.trim().toUpperCase());
        }
    }

    public int getBookedSeatsCount() {
        return bookedSeats.size();
    }

    public int getAvailableSeatsCount(int totalSeats) {
        return Math.max(0, totalSeats - bookedSeats.size());
    }
}
