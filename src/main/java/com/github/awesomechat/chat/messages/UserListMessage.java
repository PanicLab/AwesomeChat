package com.github.awesomechat.chat.messages;


import com.github.awesomechat.chat.models.User;

import java.util.List;


public class UserListMessage extends Message {
    public List<User> users;

    public UserListMessage(List<User> users){
        this.setType(4);
        this.users = users;
    }
}
