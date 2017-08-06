package com.github.awesomechat.chat.services;

import com.github.awesomechat.chat.models.User;
import com.github.awesomechat.chat.repositories.UserRepository;
import com.github.awesomechat.chat.utils.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordHasher passHasher;

    @Autowired
    private UserRepository repository;

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
