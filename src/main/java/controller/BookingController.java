package controller;

import java.util.ArrayList;
import java.util.List;

import model.Booking;
import service.BookingService;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public Booking createBooking(int userId, int showId, List<String> seatCodes, String paymentMethod) {
        return bookingService.createBooking(userId, showId, seatCodes, paymentMethod);
    }

    public boolean cancelBooking(String bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    public ArrayList<Booking> getBookingsByUser(int userId) {
        return bookingService.getBookingsByUser(userId);
    }

    public ArrayList<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
