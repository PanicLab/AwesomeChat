package com.github.awesomechat.chat.messages;


import java.util.List;


public class UserListMessage extends Message {
    public List<String> users;

    public UserListMessage(List<String> users){
        this.setType(4);
        this.users = users;
    }
}
