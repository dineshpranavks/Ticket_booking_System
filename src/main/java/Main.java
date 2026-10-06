import controller.*;
import repository.*;
import service.*;
import view.*;
import model.*;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Repositories
        UserRepository userRepository = new UserRepository();
        MovieRepository movieRepository = new MovieRepository();
        TheatreRepository theatreRepository = new TheatreRepository();
        ShowRepository showRepository = new ShowRepository();
        BookingRepository bookingRepository = new BookingRepository();
        ReviewRepository reviewRepository = new ReviewRepository();

        // Services
        UserService userService = new UserService(userRepository);
        MovieService movieService = new MovieService(movieRepository);
        TheatreService theatreService = new TheatreService(theatreRepository, showRepository);
        BookingService bookingService = new BookingService(bookingRepository, showRepository, theatreRepository, movieRepository, userRepository);
        ReviewService reviewService = new ReviewService(reviewRepository, bookingRepository, movieRepository);

        // Controllers
        UserController userController = new UserController(userService);
        MovieController movieController = new MovieController(movieService);
        TheatreController theatreController = new TheatreController(theatreService);
        BookingController bookingController = new BookingController(bookingService);
        ReviewController reviewController = new ReviewController(reviewService);

        // Initialize Seed Data
        initializeData(userRepository, movieService, theatreService, showRepository);

        // Views
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView(userController);
        MovieView movieView = new MovieView(movieController);
        AdminView adminView = new AdminView(movieView); // Can be expanded to pass other controllers

        while (true) {
            User loggedInUser = userView.showUserMenu();
            if (loggedInUser == null) break;

            if (loggedInUser.getRole().equals("ADMIN")) {
                adminView.showAdminMenu();
            } else {
                UserDashboardView dashboard = new UserDashboardView(loggedInUser, scanner, movieController, theatreController, bookingController, reviewController);
                dashboard.showDashboard();
            }
        }
        System.out.println("Application closed.");
    }

    private static void initializeData(UserRepository userRepository, MovieService movieService, TheatreService theatreService, ShowRepository showRepository) {
        userRepository.addUser(new User(1, "Admin", "admin@gmail.com", "8888888888", "admin123", "ADMIN"));
        userRepository.addUser(new User(2, "Rahul", "rahul@gmail.com", "9999999999", "pass123", "USER"));

        Movie m1 = movieService.addMovie("Oppenheimer", "Biography", "English", 180, "2024-07-21", 8.9, "NOW_SHOWING");
        Movie m2 = movieService.addMovie("Inception", "Sci-Fi", "English", 148, "2010-07-16", 8.8, "NOW_SHOWING");
        Movie m3 = movieService.addMovie("Kalki 2898 AD", "Action", "Telugu", 181, "2024-06-27", 8.2, "NOW_SHOWING");
        Movie m4 = movieService.addMovie("Avatar: Fire and Ash", "Action/Fantasy", "English", 190, "2025-12-19", 9.0, "UPCOMING");

        Theatre t1 = theatreService.addTheatre("PVR Grand Mall", "Downtown", "Mumbai");
        Theatre t2 = theatreService.addTheatre("INOX Nexus", "Westside", "Delhi");

        Screen s1 = theatreService.addScreenToTheatre(t1.getTheatreId(), "Audi 1", 5, 5); // 25 seats
        Screen s2 = theatreService.addScreenToTheatre(t1.getTheatreId(), "IMAX", 6, 8);   // 48 seats

        Show sh1 = theatreService.addShow(m1.getMovieId(), t1.getTheatreId(), s1.getScreenId(), "2026-10-10", "18:30");
        Show sh2 = theatreService.addShow(m2.getMovieId(), t1.getTheatreId(), s2.getScreenId(), "2026-10-10", "20:00");
        
        // Pre-book some seats to show seat map
        sh1.bookSeats(Arrays.asList("A1", "A2", "D4"));
    }
}