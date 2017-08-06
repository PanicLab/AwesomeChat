package com.github.awesomechat.chat.messages;


public class Message {
    public static final int JOIN = 1;
    public static final int CHAT = 2;
    public static final int USER_LIST = 4;
    public static final int QUIT = 7;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
