package com.github.awesomechat.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.awesomechat.chat.messages.*;
import com.github.awesomechat.chat.services.JsonDecoder;
import com.github.awesomechat.chat.services.JsonEncoder;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import java.io.StringReader;

@Component
public class Jsonizer implements JsonEncoder, JsonDecoder{

    @Override
    public String encode(Message message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public Message decode(String str) {
        Message message = null;
        try {
            JsonObject obj = Json.createReader(new StringReader(str)).readObject();
            ObjectMapper mapper = new ObjectMapper();

            int type = obj.getInt("type");

            switch (type) {
                case Message.JOIN: {
                    message = mapper.readValue(str, JoinMessage.class);
                    break;
                }

                case Message.CHAT: {
                    message = mapper.readValue(str, ChatMessage.class);
                    break;
                }

                case Message.GET_USERS: {
                    message = mapper.readValue(str, GetUsersMessage.class);
                    break;
                }

                case Message.USER_LIST: {
                    message = mapper.readValue(str, UserListMessage.class);
                    break;
                }

                case Message.CHAT_MESSAGES: {
                    message = mapper.readValue(str, ChatMessagesMessage.class);
                    break;
                }

                case Message.MESSAGE_LIST: {
                    message = mapper.readValue(str, UserListMessage.class);
                    break;
                }

                case Message.QUIT: {
                    message = mapper.readValue(str, QuitMessage.class);
                    break;
                }
            }

        } catch (JsonException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }
}
