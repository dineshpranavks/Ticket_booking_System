package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.MovieController;
import model.Movie;

public class UserMovieView
{
    private Scanner scanner;
    private MovieController movieController;

    public UserMovieView(MovieController movieController,Scanner scanner)
    {
        this.movieController = movieController;
        this.scanner = scanner;
    }

    public void showMovieMenu()
    {
        while (true)
        {
            System.out.println();
            System.out.println("==============================");
            System.out.println("        MOVIE BROWSING");
            System.out.println("==============================");

            System.out.println("1. View Movies");
            System.out.println("2. Search Movie");
            System.out.println("3. Filter by Genre");
            System.out.println("4. Filter by Language");
            System.out.println("5. Filter by Rating");
            System.out.println("6. Back");

            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    viewMovies();
                    break;

                case 2:
                    searchMovies();
                    break;

                case 3:
                    filterByGenre();
                    break;

                case 4:
                    filterByLanguage();
                    break;

                case 5:
                    filterByRating();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void viewMovies()
    {
        ArrayList<Movie> movies = movieController.getAllMovies();

        System.out.println();
        System.out.println("===== AVAILABLE MOVIES =====");

        if (movies.size() == 0)
        {
            System.out.println("No movies available.");
            return;
        }

        for (Movie movie : movies)
            movie.displayMovie();
    }

    private void searchMovies()
    {
        System.out.println();
        System.out.println("===== SEARCH MOVIE =====");

        System.out.print("Enter title: ");

        String title = scanner.nextLine();

        ArrayList<Movie> movies = movieController.searchMovies(title);

        if (movies.size() == 0)
        {
            System.out.println("No movies found.");
            return;
        }

        for (Movie movie : movies)
            movie.displayMovie();
    }

    private void filterByGenre()
    {
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        ArrayList<Movie> movies = movieController.filterByGenre(genre);
        displayMovies(movies);
    }

    private void filterByLanguage()
    {
        System.out.print("Enter language: ");
        String language = scanner.nextLine();

        ArrayList<Movie> movies = movieController.filterByLanguage(language);
        displayMovies(movies);
    }

    private void filterByRating()
    {
        System.out.print("Enter minimum rating: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();

        ArrayList<Movie> movies = movieController.filterByRating(rating);
        displayMovies(movies);
    }

    private void displayMovies(ArrayList<Movie> movies)
    {
        System.out.println();
        System.out.println("===== FILTERED MOVIES =====");

        if (movies.size() == 0)
        {
            System.out.println("No movies found.");
            return;
        }

        for (Movie movie : movies)
            movie.displayMovie();
    }
}