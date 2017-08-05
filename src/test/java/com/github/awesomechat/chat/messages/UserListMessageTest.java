package com.github.awesomechat.chat.messages;

import com.github.awesomechat.chat.Jsonizer;
import com.github.awesomechat.chat.models.User;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserListMessageTest {

    @Test
    public void createUserListMessage() throws Exception {
        List<String> users = new ArrayList<>();
        users.add("Sergey Kuzmin");
        users.add("Gena TheCroc");
        users.add("Peter TheGreatest");
        UserListMessage message = new UserListMessage(users);

        Jsonizer jsonizer = new Jsonizer();
        String jsonMessage = jsonizer.encode(message);
        System.out.println(jsonMessage);

        String expected = "{\"type\":4,\"users\":[\"Sergey Kuzmin\",\"Gena TheCroc\",\"Peter TheGreatest\"]}";
        assertEquals(expected, jsonMessage);
    }
}