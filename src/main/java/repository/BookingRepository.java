package repository;

import java.util.ArrayList;
import model.Booking;

public class BookingRepository {
    private ArrayList<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public Booking findById(String bookingId) {
        if (bookingId == null) return null;
        for (Booking booking : bookings) {
            if (booking.getBookingId().equalsIgnoreCase(bookingId.trim())) {
                return booking;
            }
        }
        return null;
    }

    public ArrayList<Booking> getBookingsByUserId(int userId) {
        ArrayList<Booking> result = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getUserId() == userId) {
                result.add(booking);
            }
        }
        return result;
    }

    public ArrayList<Booking> getAllBookings() {
        return bookings;
    }

    public boolean updateBooking(Booking updatedBooking) {
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getBookingId().equalsIgnoreCase(updatedBooking.getBookingId())) {
                bookings.set(i, updatedBooking);
                return true;
            }
        }
        return false;
    }
}
