package service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Booking;
import model.Movie;
import model.Screen;
import model.Seat;
import model.Show;
import model.Theatre;
import model.User;
import repository.BookingRepository;
import repository.MovieRepository;
import repository.ShowRepository;
import repository.TheatreRepository;
import repository.UserRepository;

public class BookingService {
    private BookingRepository bookingRepository;
    private ShowRepository showRepository;
    private TheatreRepository theatreRepository;
    private MovieRepository movieRepository;
    private UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, ShowRepository showRepository,
                          TheatreRepository theatreRepository, MovieRepository movieRepository,
                          UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.theatreRepository = theatreRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    public Booking createBooking(int userId, int showId, List<String> seatCodes, String paymentMethod) {
        Show show = showRepository.findById(showId);
        if (show == null || show.isCancelled()) return null;

        User user = userRepository.findById(userId);
        if (user == null) return null;

        Movie movie = movieRepository.findById(show.getMovieId());
        Theatre theatre = theatreRepository.findById(show.getTheatreId());
        Screen screen = theatre.getScreenById(show.getScreenId());

        if (movie == null || theatre == null || screen == null) return null;

        double totalAmount = 0.0;
        List<String> validSeatCodes = new ArrayList<>();

        for (String code : seatCodes) {
            Seat seat = screen.getSeatByCode(code);
            if (seat == null) return null; // Invalid seat
            if (show.isSeatBooked(code)) return null; // Already booked
            totalAmount += seat.getPrice() * show.getBasePriceMultiplier();
            validSeatCodes.add(seat.getSeatCode().toUpperCase());
        }

        // Apply convenience fee (e.g., 10%)
        totalAmount = totalAmount * 1.10;

        // Try booking
        if (!show.bookSeats(validSeatCodes)) {
            return null; // Concurrent booking conflict
        }

        String paymentId = "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String bookingId = "BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Booking booking = new Booking(bookingId, userId, user.getName(), showId, movie.getTitle(),
                theatre.getName(), screen.getScreenName(), show.getShowDate(), show.getShowTime(),
                validSeatCodes, totalAmount, paymentId, paymentMethod);

        bookingRepository.addBooking(booking);
        return booking;
    }

    public boolean cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null || !booking.isConfirmed()) return false;

        Show show = showRepository.findById(booking.getShowId());
        if (show != null) {
            show.cancelSeats(booking.getSeatNumbers());
        }

        // Simple refund logic: 90% refund
        double refund = booking.getTotalAmount() * 0.90;
        booking.cancelBooking(refund);
        bookingRepository.updateBooking(booking);
        return true;
    }

    public ArrayList<Booking> getBookingsByUser(int userId) {
        return bookingRepository.getBookingsByUserId(userId);
    }

    public ArrayList<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }
}
