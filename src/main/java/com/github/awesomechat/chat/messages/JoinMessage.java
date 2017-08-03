package com.github.awesomechat.chat.messages;


public class JoinMessage extends Message {
    public static final int JOIN = 1;
    public static final int MESSAGE = 2;
    public static final int GET_USERS = 3;
    public static final int USER_LIST = 4;
    public static final int GET_MESSAGES = 5;
    public static final int MESSAGE_LIST = 6;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
