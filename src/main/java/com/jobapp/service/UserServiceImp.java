package com.jobapp.service;

import com.jobapp.entity.User;
import com.jobapp.exception.ErrorHandling;
import com.jobapp.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserServiceImp implements UserService {

    private UserDao userDao;

    private static final Logger logger = Logger.getLogger(UserServiceImp.class.getName());


    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("Get All users method used");
        return userDao.findAll();
    }

    @Override
    public User getOneUser(long userId) {
        return userDao.findById(userId).orElseThrow(() -> new ErrorHandling("Object Not Found !!!"));
    }

    @Override
    public User createUser(User user) {
        logger.info("User saved by createUser method");
        return userDao.save(user);
    }

    @Override
    public User updateUser(long userId, User user) {
        Optional<User> user1 = userDao.findById(userId);
        if (user1.isPresent()) {
            User user2 = user1.get();
            user2 = user;
            user2.setId(userId);
            logger.info("User Update method used");
            return userDao.save(user2);
        }
        throw new ErrorHandling("This is not acceptable Id");
    }

    @Override
    public void deleteById(long userId) {
        logger.info("User Delete by Id by delete method");
        userDao.deleteById(userId);
    }
}
