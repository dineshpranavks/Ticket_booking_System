package view;

import java.util.Scanner;

public class AdminView
{
    private Scanner scanner;
    private MovieView movieView;

    public AdminView(MovieView movieView)
    {
        this.scanner = new Scanner(System.in);
        this.movieView = movieView;
    }

    public void showAdminMenu()
    {
        while (true)
        {
            System.out.println();
            System.out.println("==============================");
            System.out.println("          ADMIN MENU");
            System.out.println("==============================");

            System.out.println("1. Movie Management");
            System.out.println("2. Logout");

            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    movieView.showAdminMovieMenu();
                    break;

                case 2:
                    return;

                default:
                    System.out.println( "Invalid choice");
            }
        }
    }
}