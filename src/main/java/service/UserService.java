package service;

import model.User;
import repository.UserRepository;

public class UserService
{
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) 
    {
        this.userRepository = userRepository;
    }

    public User registerUser(String name, String email, String phone, String password)
    {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty())
            return null;

        if (userRepository.emailExists(email))
            return null;

        int userId = userRepository.getAllUsers().size() + 1;

        User user = new User(userId, name, email, phone, password, "USER");

        userRepository.addUser(user);

        return user;
    }

    public User loginUser(String email, String password)
    {
        if (email == null || password == null)
            return null;

        User user = userRepository.findByEmail(email);

        if (user == null)
            return null;

        if (!user.getPassword().equals(password))
            return null;

        return user;
    }

    public User getUserById(int userId)
    {
        return userRepository.findById(userId);
    }

    public boolean updateProfile(int userId, String newName, String newPhone)
    {
        User user = userRepository.findById(userId);
        if (user == null)
            return false;

        if (newName != null && !newName.trim().isEmpty())
            user.setName(newName.trim());

        if (newPhone != null && !newPhone.trim().isEmpty())
            user.setPhone(newPhone.trim());

        return userRepository.updateUser(user);
    }

    public boolean changePassword(int userId, String oldPassword, String newPassword)
    {
        User user = userRepository.findById(userId);
        if (user == null)
            return false;

        if (!user.getPassword().equals(oldPassword))
            return false;

        if (newPassword == null || newPassword.trim().isEmpty())
            return false;

        user.setPassword(newPassword.trim());
        return userRepository.updateUser(user);
    }
}