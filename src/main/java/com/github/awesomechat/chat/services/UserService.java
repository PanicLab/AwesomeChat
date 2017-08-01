package com.github.awesomechat.chat.services;

import com.github.awesomechat.chat.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public boolean isValid(User user) {
        return true;
    }
}
