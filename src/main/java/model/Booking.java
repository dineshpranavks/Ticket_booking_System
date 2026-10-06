package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Booking {
    private String bookingId;
    private int userId;
    private String userName;
    private int showId;
    private String movieTitle;
    private String theatreName;
    private String screenName;
    private String showDate;
    private String showTime;
    private List<String> seatNumbers;
    private double totalAmount;
    private String paymentId;
    private String paymentMethod;
    private String bookingStatus; // "CONFIRMED", "CANCELLED"
    private double refundAmount;
    private String bookingTimestamp;
    private String cancellationTimestamp;

    public Booking(String bookingId, int userId, String userName, int showId, String movieTitle,
                   String theatreName, String screenName, String showDate, String showTime,
                   List<String> seatNumbers, double totalAmount, String paymentId, String paymentMethod) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.userName = userName;
        this.showId = showId;
        this.movieTitle = movieTitle;
        this.theatreName = theatreName;
        this.screenName = screenName;
        this.showDate = showDate;
        this.showTime = showTime;
        this.seatNumbers = seatNumbers;
        this.totalAmount = totalAmount;
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.bookingStatus = "CONFIRMED";
        this.refundAmount = 0.0;
        this.bookingTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.cancellationTimestamp = null;
    }

    public String getBookingId() {
        return bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getShowId() {
        return showId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getShowDate() {
        return showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public List<String> getSeatNumbers() {
        return seatNumbers;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public String getBookingTimestamp() {
        return bookingTimestamp;
    }

    public String getCancellationTimestamp() {
        return cancellationTimestamp;
    }

    public void cancelBooking(double refund) {
        this.bookingStatus = "CANCELLED";
        this.refundAmount = refund;
        this.cancellationTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public boolean isConfirmed() {
        return "CONFIRMED".equalsIgnoreCase(bookingStatus);
    }
}
