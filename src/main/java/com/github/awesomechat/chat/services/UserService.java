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
        return true;
    }

    public boolean isExist(User user) {
        return false;
    }

    public void createUser(User user, String password) {

    }
}
