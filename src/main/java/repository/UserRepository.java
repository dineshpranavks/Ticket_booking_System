package repository;

import java.util.ArrayList;
import model.User;

public class UserRepository {
    private ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public User findByEmail(String email) {
        if (email == null) return null;
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email.trim())) {
                return user;
            }
        }
        return null;
    }

    public User findById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    public boolean emailExists(String email) {
        return findByEmail(email) != null;
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public boolean updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId() == updatedUser.getUserId()) {
                users.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }
}