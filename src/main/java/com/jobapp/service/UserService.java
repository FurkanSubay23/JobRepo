package com.jobapp.service;

import com.jobapp.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getOneUser(long userId);

    User createUser(User user);

    User updateUser(long userId, User user);

    void deleteById(long userId);
}
