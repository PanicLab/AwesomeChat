package com.github.awesomechat.chat.services;

import com.github.awesomechat.chat.models.User;
import com.github.awesomechat.chat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    UserService(UserRepository ur) {
        this.repository = ur;
    }

    public boolean isValid(User user, String password) {
        return repository.isExist(user) && repository.isPasswordValid(user, password);
    }

    public boolean isExist(User user) {
        return repository.isExist(user);
    }

    public void createUser(User user, String password) {
        repository.createUser(user, password);
    }
}
