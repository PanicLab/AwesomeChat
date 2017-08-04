package com.github.awesomechat.chat.messages;

import java.util.Date;

public class ChatMessage extends Message {

    private String message;
    private String author;
    private Date timeSent;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    public Date getTimeSent() {
        return timeSent;
    }
}
