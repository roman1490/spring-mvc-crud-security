package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void updateUser(int id, User newUser);
    void deleteUser(int id);
    User getUser(int id);
    List<User> getAllUsers();
}
