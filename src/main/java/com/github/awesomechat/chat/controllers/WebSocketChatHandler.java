package com.github.awesomechat.chat.controllers;


import com.github.awesomechat.chat.messages.JoinMessage;
import com.github.awesomechat.chat.messages.Message;
import com.github.awesomechat.chat.models.User;
import com.github.awesomechat.chat.services.JsonDecoder;
import com.github.awesomechat.chat.services.JsonEncoder;
import com.github.awesomechat.chat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Controller
public class WebSocketChatHandler extends TextWebSocketHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private JsonEncoder encoder;
    @Autowired
    private JsonDecoder decoder;

    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private static final List<User> users = Collections.synchronizedList(new ArrayList<>());

/*    private SimpMessagingTemplate template;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }*/

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //super.afterConnectionEstablished(session);
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //super.handleTextMessage(session, message);
        /*Создать бин джсона
        * Заинжектить
        * Входящее раскодировать из джсона
        * Узнать его тип
        * блок свич по каждому типу создает ответное сообщение
        * броадкаст обратно
        * */
/*        Message m = new Message();
        TextMessage mmm = new TextMessage("");


        ObjectMapper mapper = new ObjectMapper();
        //set text to message
        //send m*/
        String json = message.getPayload();
        parseMessage(json);


/*        for(WebSocketSession wss : sessions) {
            try {
                TextMessage newMessage = new TextMessage("Online!");
                wss.sendMessage(newMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    private void parseMessage(String json) {
        Message message = decoder.decode(json);
        if (message instanceof JoinMessage) {
            process(JoinMessage.class.cast(message));
            broadcast(json);
            return;
        }

        String unknown = "Unknown message type detected!";
        broadcast(unknown);
    }

    private void process(JoinMessage message) {
        String firstName = message.getName().split(" ")[0];
        String lastName = message.getName().split(" ")[1];
        users.add(new User(firstName, lastName));
    }

    private void broadcast(String json) {
        TextMessage message = new TextMessage(json);
        for (WebSocketSession wss: sessions) {
            try {
                wss.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //super.afterConnectionClosed(session, status);
        sessions.remove(session);
    }


}
