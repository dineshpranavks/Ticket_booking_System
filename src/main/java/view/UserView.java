package view;

import java.util.*;

import controller.UserController;
import model.User;

public class UserView
{
    private Scanner scanner;
    private UserController userController;

    public UserView(UserController userController)
    {
        scanner = new Scanner(System.in);
        this.userController = userController;
    }

    public User showUserMenu()
    {
        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("       MOVIE BOOKING APP");
            System.out.println("================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    register();
                    break;

                case 2:

                    User user = login();

                    if (user != null)
                        return user;
                    break;

                case 3:
                    System.out.println("Thank you!");
                    return null;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void register()
    {
        System.out.println();
        System.out.println("===== USER REGISTRATION =====");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean success = userController.register(name,email,phone,password);

        if (success)
            System.out.println("Registration successful!");
        else
            System.out.println("Email already exists!");
    }

    private User login()
    {
        System.out.println();
        System.out.println("===== LOGIN =====");

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user =userController.login(email,password);

        if (user == null)
        {
            System.out.println("Invalid email or password!");
            return null;
        }

        System.out.println();
        System.out.println("Login successful!");

        System.out.println("Welcome " + user.getName() + "!");
        return user;
    }
}