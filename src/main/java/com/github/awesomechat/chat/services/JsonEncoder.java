package com.github.awesomechat.chat.services;

import com.github.awesomechat.chat.messages.Message;

public interface JsonEncoder {
    public String encode(Message message);
}
