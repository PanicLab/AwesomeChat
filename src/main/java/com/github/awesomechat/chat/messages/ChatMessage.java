package com.github.awesomechat.chat.messages;


public class ChatMessage extends Message {

    private String message;
    private String author;

    public ChatMessage() {
        setType(Message.CHAT);
    }

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
}
