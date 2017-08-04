package com.github.awesomechat.chat.services;

import com.github.awesomechat.chat.messages.Message;

public interface JsonDecoder {
    public Message decode(String str);
}
