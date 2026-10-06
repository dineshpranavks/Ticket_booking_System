package model;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private int screenId;
    private int theatreId;
    private String screenName;
    private int totalRows;
    private int seatsPerRow;
    private List<Seat> seats;

    public Screen(int screenId, int theatreId, String screenName, int totalRows, int seatsPerRow) {
        this.screenId = screenId;
        this.theatreId = theatreId;
        this.screenName = screenName;
        this.totalRows = totalRows;
        this.seatsPerRow = seatsPerRow;
        this.seats = new ArrayList<>();
        initializeSeats(150.0, 250.0, 400.0);
    }

    public Screen(int screenId, int theatreId, String screenName, int totalRows, int seatsPerRow,
                  double standardPrice, double premiumPrice, double reclinerPrice) {
        this.screenId = screenId;
        this.theatreId = theatreId;
        this.screenName = screenName;
        this.totalRows = totalRows;
        this.seatsPerRow = seatsPerRow;
        this.seats = new ArrayList<>();
        initializeSeats(standardPrice, premiumPrice, reclinerPrice);
    }

    private void initializeSeats(double standardPrice, double premiumPrice, double reclinerPrice) {
        seats.clear();
        for (int r = 0; r < totalRows; r++) {
            char rowChar = (char) ('A' + r);
            String tier;
            double price;

            if (r < 2) {
                tier = "STANDARD";
                price = standardPrice;
            } else if (r < totalRows - 1) {
                tier = "PREMIUM";
                price = premiumPrice;
            } else {
                tier = "RECLINER";
                price = reclinerPrice;
            }

            for (int col = 1; col <= seatsPerRow; col++) {
                String seatCode = "" + rowChar + col;
                seats.add(new Seat(seatCode, rowChar, col, tier, price));
            }
        }
    }

    public int getScreenId() {
        return screenId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public String getScreenName() {
        return screenName;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public int getTotalSeats() {
        return totalRows * seatsPerRow;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Seat getSeatByCode(String code) {
        if (code == null) return null;
        for (Seat seat : seats) {
            if (seat.getSeatCode().equalsIgnoreCase(code.trim())) {
                return seat;
            }
        }
        return null;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
