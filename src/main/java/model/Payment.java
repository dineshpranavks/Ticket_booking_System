package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payment {
    private String paymentId;
    private String bookingId;
    private int userId;
    private double amount;
    private String paymentMethod; // "CARD", "UPI", "NET_BANKING"
    private String paymentStatus; // "SUCCESS", "REFUNDED"
    private String transactionRef;
    private String paymentTimestamp;

    public Payment(String paymentId, String bookingId, int userId, double amount, String paymentMethod, String paymentStatus) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.transactionRef = "TXN-" + System.currentTimeMillis();
        this.paymentTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public String getPaymentTimestamp() {
        return paymentTimestamp;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
