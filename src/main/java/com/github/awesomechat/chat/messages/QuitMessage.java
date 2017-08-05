package com.github.awesomechat.chat.messages;

public class QuitMessage extends Message {

    private String name;

    public QuitMessage() {
        super();
        this.setType(Message.QUIT);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
