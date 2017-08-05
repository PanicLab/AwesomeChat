package com.github.awesomechat.chat.messages;

import com.github.awesomechat.chat.Jsonizer;
import com.github.awesomechat.chat.models.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserListMessageTest {

    @Test
    public void createUserListMessage() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("Sergey", "Kuzmin"));
        users.add(new User("Gena", "TheCroc"));
        users.add(new User("Peter", "TheGreatest"));
        UserListMessage message = new UserListMessage(users);

        Jsonizer jsonizer = new Jsonizer();
        String jsonMessage = jsonizer.encode(message);
        System.out.println(jsonMessage);

        String expected = "{\"type\":4,\"users\":[{\"firstName\":\"Sergey\",\"lastName\":\"Kuzmin\",\"fullName\":" +
                "\"Sergey Kuzmin\"},{\"firstName\":\"Gena\",\"lastName\":\"TheCroc\",\"fullName\":\"Gena TheCroc\"}," +
                "{\"firstName\":\"Peter\",\"lastName\":\"TheGreatest\",\"fullName\":\"Peter TheGreatest\"}]}";
        assertEquals(expected, jsonMessage);
    }

}