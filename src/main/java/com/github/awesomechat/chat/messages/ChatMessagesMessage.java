package com.github.awesomechat.chat.messages;

import java.util.List;


public class ChatMessagesMessage extends Message {
    public List<ChatMessage> messages;

    public ChatMessagesMessage(List<ChatMessage> messages) {
        this.setType(6);
        this.messages = messages;
    }
}
