package model;

public class Seat {
    private String seatCode;
    private char row;
    private int number;
    private String tier; // "STANDARD", "PREMIUM", "RECLINER"
    private double price;

    public Seat(String seatCode, char row, int number, String tier, double price) {
        this.seatCode = seatCode;
        this.row = row;
        this.number = number;
        this.tier = tier;
        this.price = price;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public char getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public String getTier() {
        return tier;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return seatCode + " (" + tier + " - Rs." + price + ")";
    }
}
