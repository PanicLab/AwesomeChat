package com.github.awesomechat.chat.messages;


public class JoinMessage extends Message {

    private String name;

    public JoinMessage() {
        super();
        this.setType(Message.JOIN);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
