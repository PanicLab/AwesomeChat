package com.github.awesomechat.chat.services;


import com.github.awesomechat.chat.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JsonService {

    private JsonEncoder encoder;
    private JsonDecoder decoder;

    @Autowired
    JsonService(JsonEncoder e, JsonDecoder d) {
        encoder = e;
        decoder = d;
    }

    public String encode(Message message) {
        return encoder.encode(message);
    }

    public Message decode(String str) {
        return decoder.decode(str);
    }
}
