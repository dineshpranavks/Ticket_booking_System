package view;

import java.util.*;

import controller.MovieController;
import model.Movie;

public class MovieView
{
    private Scanner scanner;
    private MovieController movieController;

    public MovieView(MovieController movieController)
    {
        this.scanner = new Scanner(System.in);
        this.movieController = movieController;
    }

    public void showAdminMovieMenu()
    {
        while (true)
            {
            System.out.println();
            System.out.println("==============================");
            System.out.println("       MOVIE MANAGEMENT");
            System.out.println("==============================");

            System.out.println("1. Add Movie");
            System.out.println("2. View Movies");
            System.out.println("3. Update Movie");
            System.out.println("4. Delete Movie");
            System.out.println("5. Back");

            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    addMovie();
                    break;

                case 2:
                    viewMovies();
                    break;

                case 3:
                    updateMovie();
                    break;

                case 4:
                    deleteMovie();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void addMovie()
    {
        System.out.println();
        System.out.println("===== ADD MOVIE =====");

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter language: ");
        String language = scanner.nextLine();

        System.out.print("Enter duration (minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter release date: ");
        String releaseDate = scanner.nextLine();

        System.out.print("Enter rating: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();

        Movie movie = movieController.addMovie(title,genre,language,duration,releaseDate,rating);

        System.out.println();
        System.out.println("Movie added successfully!");

        System.out.println("Movie ID: " + movie.getMovieId());
    }

    private void viewMovies()
    {
        System.out.println();
        System.out.println("===== MOVIES =====");

        ArrayList<Movie> movies = movieController.getAllMovies();

        if (movies.size() == 0)
        {
            System.out.println("No movies available.");
            return;
        }

        for (Movie movie : movies)
            movie.displayMovie();
    }

    private void updateMovie()
    {
        System.out.println();
        System.out.println("===== UPDATE MOVIE =====");

        System.out.print("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        Movie existingMovie = movieController.getMovieById(movieId);

        if (existingMovie == null)
        {
            System.out.println("Movie not found.");
            return;
        }

        System.out.print("Enter new title: ");
        String title = scanner.nextLine();

        System.out.print("Enter new genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter new language: ");
        String language = scanner.nextLine();

        System.out.print("Enter new duration: ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new release date: ");
        String releaseDate = scanner.nextLine();

        System.out.print("Enter new rating: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();

        boolean success = movieController.updateMovie(movieId,title,genre,language,duration,releaseDate,rating);

        if (success)
            System.out.println("Movie updated successfully!");

        else
            System.out.println("Movie update failed.");
    }

    private void deleteMovie()
    {
        System.out.println();
        System.out.println("===== DELETE MOVIE =====");

        System.out.print("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        Movie movie = movieController.getMovieById(movieId);

        if (movie == null)
        {
            System.out.println("Movie not found.");
            return;
        }

        System.out.print("Are you sure? (yes/no): ");

        String answer = scanner.nextLine();

        if (!answer.equalsIgnoreCase("yes"))
        {
            System.out.println("Delete cancelled.");
            return;
        }

        boolean success = movieController.deleteMovie(movieId);

        if (success)
            System.out.println("Movie deleted successfully!");

        else
            System.out.println("Movie deletion failed.");
    }

    // private void searchMovies()
    // {
    //     System.out.println();
    //     System.out.println("===== SEARCH MOVIE =====");

    //     System.out.print("Enter movie title: ");
    //     String title = scanner.nextLine();

    //     ArrayList<Movie> movies = movieController.searchMovies(title);

    //     if (movies.size() == 0)
    //     {
    //         System.out.println("No movies found.");
    //         return;
    //     }

    //     for (Movie movie : movies)
    //         movie.displayMovie();
    // }
}