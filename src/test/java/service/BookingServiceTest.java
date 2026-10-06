package service;

import model.Booking;
import model.Movie;
import model.Screen;
import model.Show;
import model.Theatre;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookingRepository;
import repository.MovieRepository;
import repository.ShowRepository;
import repository.TheatreRepository;
import repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    private BookingRepository bookingRepository;
    private ShowRepository showRepository;
    private TheatreRepository theatreRepository;
    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingRepository = new BookingRepository();
        showRepository = new ShowRepository();
        theatreRepository = new TheatreRepository();
        movieRepository = new MovieRepository();
        userRepository = new UserRepository();
        bookingService = new BookingService(bookingRepository, showRepository, theatreRepository, movieRepository, userRepository);

        // Setup test data
        User user = new User(1, "Test User", "test@gmail.com", "1234567890", "password", "USER");
        userRepository.addUser(user);

        Movie movie = new Movie(1, "Test Movie", "Action", "English", 120, "2026-01-01", 8.0, "NOW_SHOWING");
        movieRepository.addMovie(movie);

        Theatre theatre = new Theatre(1, "Test Theatre", "Test Location", "Test City");
        Screen screen = new Screen(1, 1, "Screen 1", 5, 5, 100.0, 150.0, 200.0);
        theatre.addScreen(screen);
        theatreRepository.addTheatre(theatre);

        Show show = new Show(1, 1, 1, 1, "2026-10-10", "18:00");
        showRepository.addShow(show);
    }

    @Test
    void testCreateBookingSuccess() {
        List<String> seats = Arrays.asList("A1", "A2");
        Booking booking = bookingService.createBooking(1, 1, seats, "CARD");

        assertNotNull(booking, "Booking should be created successfully");
        assertEquals(1, booking.getUserId(), "User ID should match");
        assertEquals(1, booking.getShowId(), "Show ID should match");
        assertEquals(2, booking.getSeatNumbers().size(), "Should have 2 seats");
        assertEquals("CONFIRMED", booking.getBookingStatus(), "Status should be CONFIRMED");
        assertEquals(1, bookingRepository.getAllBookings().size(), "Booking should be saved in repository");
        
        Show show = showRepository.findById(1);
        assertTrue(show.isSeatBooked("A1"), "Seat A1 should be booked");
        assertTrue(show.isSeatBooked("A2"), "Seat A2 should be booked");
    }

    @Test
    void testCreateBookingInvalidUser() {
        List<String> seats = Arrays.asList("A1", "A2");
        Booking booking = bookingService.createBooking(99, 1, seats, "CARD");
        assertNull(booking, "Booking should fail for invalid user");
    }

    @Test
    void testCreateBookingInvalidShow() {
        List<String> seats = Arrays.asList("A1", "A2");
        Booking booking = bookingService.createBooking(1, 99, seats, "CARD");
        assertNull(booking, "Booking should fail for invalid show");
    }

    @Test
    void testCreateBookingSeatAlreadyBooked() {
        List<String> seats = Arrays.asList("A1", "A2");
        bookingService.createBooking(1, 1, seats, "CARD");

        // Try booking the same seat again
        List<String> seats2 = Arrays.asList("A1", "B1");
        Booking booking2 = bookingService.createBooking(1, 1, seats2, "CARD");
        
        assertNull(booking2, "Booking should fail if seat is already booked");
    }

    @Test
    void testCancelBookingSuccess() {
        List<String> seats = Arrays.asList("A1", "A2");
        Booking booking = bookingService.createBooking(1, 1, seats, "CARD");
        assertNotNull(booking);

        boolean cancelled = bookingService.cancelBooking(booking.getBookingId());
        assertTrue(cancelled, "Booking should be cancelled successfully");
        
        Booking updatedBooking = bookingRepository.findById(booking.getBookingId());
        assertEquals("CANCELLED", updatedBooking.getBookingStatus(), "Status should be CANCELLED");
        
        Show show = showRepository.findById(1);
        assertFalse(show.isSeatBooked("A1"), "Seat A1 should be released");
        assertFalse(show.isSeatBooked("A2"), "Seat A2 should be released");
    }

    @Test
    void testCancelBookingInvalidId() {
        boolean cancelled = bookingService.cancelBooking("INVALID-ID");
        assertFalse(cancelled, "Should return false for invalid booking ID");
    }
}
