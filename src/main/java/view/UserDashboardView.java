package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import controller.BookingController;
import controller.MovieController;
import controller.ReviewController;
import controller.TheatreController;
import model.Booking;
import model.Movie;
import model.Review;
import model.Screen;
import model.Seat;
import model.Show;
import model.User;

public class UserDashboardView {
    private User loggedInUser;
    private Scanner scanner;
    private MovieController movieController;
    private TheatreController theatreController;
    private BookingController bookingController;
    private ReviewController reviewController;

    public UserDashboardView(User loggedInUser, Scanner scanner, MovieController movieController,
                             TheatreController theatreController, BookingController bookingController,
                             ReviewController reviewController) {
        this.loggedInUser = loggedInUser;
        this.scanner = scanner;
        this.movieController = movieController;
        this.theatreController = theatreController;
        this.bookingController = bookingController;
        this.reviewController = reviewController;
    }

    public void showDashboard() {
        while (true) {
            System.out.println("\n==============================================");
            System.out.println("   WELCOME " + loggedInUser.getName().toUpperCase() + " TO MOVIE BOOKING APP");
            System.out.println("==============================================");
            System.out.println("1. Browse Movies (Now Showing / Upcoming)");
            System.out.println("2. Search & Filter Movies");
            System.out.println("3. Book Tickets");
            System.out.println("4. My Bookings & Cancellations");
            System.out.println("5. Rate & Review a Movie");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.nextLine(); // clear
                continue;
            }
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    browseMovies();
                    break;
                case 2:
                    searchAndFilterMovies();
                    break;
                case 3:
                    bookTickets();
                    break;
                case 4:
                    manageBookings();
                    break;
                case 5:
                    rateAndReview();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void browseMovies() {
        System.out.println("\n--- NOW SHOWING ---");
        ArrayList<Movie> nowShowing = movieController.getNowShowingMovies();
        if (nowShowing.isEmpty()) System.out.println("No movies currently showing.");
        else for (Movie m : nowShowing) m.displayMovie();

        System.out.println("\n--- UPCOMING MOVIES ---");
        ArrayList<Movie> upcoming = movieController.getUpcomingMovies();
        if (upcoming.isEmpty()) System.out.println("No upcoming movies.");
        else for (Movie m : upcoming) m.displayMovie();
    }

    private void searchAndFilterMovies() {
        System.out.println("\n--- SEARCH & FILTER ---");
        System.out.println("1. By Title");
        System.out.println("2. By Genre");
        System.out.println("3. By Language");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter query: ");
        String query = scanner.nextLine();
        
        ArrayList<Movie> results = new ArrayList<>();
        if (choice == 1) results = movieController.searchMovies(query);
        else if (choice == 2) results = movieController.filterByGenre(query);
        else if (choice == 3) results = movieController.filterByLanguage(query);
        
        if (results.isEmpty()) {
            System.out.println("No movies found matching your query.");
        } else {
            for (Movie m : results) m.displayMovie();
        }
    }

    private void bookTickets() {
        System.out.println("\n=== BOOK TICKETS ===");
        System.out.print("Enter Movie ID you want to watch: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();
        
        Movie movie = movieController.getMovieById(movieId);
        if (movie == null || !"NOW_SHOWING".equalsIgnoreCase(movie.getStatus())) {
            System.out.println("Invalid Movie ID or Movie is not currently showing.");
            return;
        }

        ArrayList<Show> shows = theatreController.getShowsByMovie(movieId);
        if (shows.isEmpty()) {
            System.out.println("No shows available for this movie right now.");
            return;
        }

        System.out.println("\nAvailable Shows:");
        for (Show s : shows) {
            System.out.println("Show ID: " + s.getShowId() + " | Date: " + s.getShowDate() + " | Time: " + s.getShowTime());
        }

        System.out.print("\nEnter Show ID: ");
        int showId = scanner.nextInt();
        scanner.nextLine();

        Show show = theatreController.getShowById(showId);
        if (show == null || show.getMovieId() != movieId) {
            System.out.println("Invalid Show ID.");
            return;
        }

        Screen screen = theatreController.getTheatreById(show.getTheatreId()).getScreenById(show.getScreenId());
        
        displaySeatMap(screen, show);

        System.out.print("\nEnter comma-separated seat codes to book (e.g. A1, A2, B5): ");
        String seatInput = scanner.nextLine();
        String[] seatArr = seatInput.split(",");
        List<String> seatsToBook = new ArrayList<>();
        for (String s : seatArr) {
            seatsToBook.add(s.trim().toUpperCase());
        }

        System.out.println("\nSelect Payment Method:");
        System.out.println("1. Credit/Debit Card");
        System.out.println("2. UPI");
        System.out.println("3. Net Banking");
        System.out.print("Choice: ");
        int pChoice = scanner.nextInt();
        scanner.nextLine();
        
        String method = "CARD";
        if (pChoice == 2) method = "UPI";
        else if (pChoice == 3) method = "NET_BANKING";

        System.out.println("Processing payment...");
        Booking booking = bookingController.createBooking(loggedInUser.getUserId(), showId, seatsToBook, method);
        
        if (booking == null) {
            System.out.println("Booking failed! Some seats might be unavailable or invalid.");
        } else {
            System.out.println("\nBOOKING SUCCESSFUL!");
            displayTicket(booking);
        }
    }

    private void displaySeatMap(Screen screen, Show show) {
        System.out.println("\n--- SEAT MAP ---");
        System.out.println("           [========= SCREEN =========]\n");
        for (int r = 0; r < screen.getTotalRows(); r++) {
            char rowChar = (char) ('A' + r);
            System.out.print(rowChar + " ");
            for (int col = 1; col <= screen.getSeatsPerRow(); col++) {
                String code = "" + rowChar + col;
                if (show.isSeatBooked(code)) {
                    System.out.print("[XX] ");
                } else {
                    Seat seat = screen.getSeatByCode(code);
                    String t = seat.getTier();
                    String symbol = t.equals("RECLINER") ? "R" : (t.equals("PREMIUM") ? "P" : "S");
                    System.out.print("[" + String.format("%02d", col) + symbol + "] ");
                }
            }
            System.out.println();
        }
        System.out.println("\nLegend: [XX] Booked | [S] Standard | [P] Premium | [R] Recliner");
    }

    private void displayTicket(Booking b) {
        System.out.println("************************************************");
        System.out.println("*                E-TICKET                      *");
        System.out.println("************************************************");
        System.out.println("Booking ID  : " + b.getBookingId());
        System.out.println("Movie       : " + b.getMovieTitle());
        System.out.println("Theatre     : " + b.getTheatreName() + " (" + b.getScreenName() + ")");
        System.out.println("Date & Time : " + b.getShowDate() + " | " + b.getShowTime());
        System.out.println("Seats       : " + b.getSeatNumbers());
        System.out.println("Total Amount: Rs. " + String.format("%.2f", b.getTotalAmount()));
        System.out.println("Status      : " + b.getBookingStatus());
        System.out.println("************************************************");
        System.out.println("       || ||| || ||| || ||| || ||| ||           ");
        System.out.println("************************************************");
    }

    private void manageBookings() {
        ArrayList<Booking> myBookings = bookingController.getBookingsByUser(loggedInUser.getUserId());
        if (myBookings.isEmpty()) {
            System.out.println("You have no bookings.");
            return;
        }

        System.out.println("\n=== MY BOOKINGS ===");
        for (Booking b : myBookings) {
            System.out.println(b.getBookingId() + " | " + b.getMovieTitle() + " | " + b.getShowDate() + " | Status: " + b.getBookingStatus());
        }

        System.out.print("\nEnter Booking ID to view details or cancel (or type 'back'): ");
        String bId = scanner.nextLine();
        if (bId.equalsIgnoreCase("back")) return;

        Booking b = null;
        for (Booking temp : myBookings) {
            if (temp.getBookingId().equalsIgnoreCase(bId)) {
                b = temp;
                break;
            }
        }

        if (b == null) {
            System.out.println("Invalid Booking ID.");
            return;
        }

        displayTicket(b);

        if (b.isConfirmed()) {
            System.out.print("\nDo you want to CANCEL this booking? (yes/no): ");
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("yes")) {
                if (bookingController.cancelBooking(b.getBookingId())) {
                    System.out.println("Booking cancelled successfully! Refund initiated.");
                } else {
                    System.out.println("Failed to cancel booking.");
                }
            }
        }
    }

    private void rateAndReview() {
        System.out.println("\n=== RATE & REVIEW ===");
        System.out.print("Enter Movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Rating (1.0 to 5.0): ");
        double rating = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter your review comment: ");
        String comment = scanner.nextLine();

        Review r = reviewController.addReview(loggedInUser.getUserId(), loggedInUser.getName(), movieId, rating, comment);
        if (r != null) {
            System.out.println("Review submitted successfully! Thank you.");
        } else {
            System.out.println("Review failed. Ensure you have booked this movie and haven't reviewed it already.");
        }
    }
}
