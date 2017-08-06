package com.github.awesomechat.chat.repositories;

import com.github.awesomechat.chat.models.User;

public interface UserRepository {

    boolean isExist(User user);
    boolean isPasswordValid(User user, String password);
    void createUser(User user, String password);
}
