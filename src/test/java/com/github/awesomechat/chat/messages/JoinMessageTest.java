package com.github.awesomechat.chat.messages;

import com.github.awesomechat.chat.utils.Jsonizer;
import org.junit.Test;

import static org.junit.Assert.*;

public class JoinMessageTest {

    @Test
    public void create() {

        JoinMessage message = new JoinMessage();
        message.setName("Sergey");

        Jsonizer jsonizer = new Jsonizer();
        String jsonMessage = jsonizer.encode(message);
        System.out.println(jsonMessage);

        String expected = "{\"type\":1,\"name\":\"Sergey\"}";
        assertEquals(expected, jsonMessage);
    }

}