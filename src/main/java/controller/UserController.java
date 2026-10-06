package controller;

import model.User;
import service.UserService;

public class UserController
{
    private UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    public boolean register(String name,String email,String phone,String password)
    {
        User user = userService.registerUser(name,email,phone,password);

        if (user == null)
            return false;

        return true;
    }

    public User login(String email,String password)
    {
        return userService.loginUser(email,password);
    }
}