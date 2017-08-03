package com.github.awesomechat.chat.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.awesomechat.chat.messages.Message;
import com.github.awesomechat.chat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Controller
public class WebSocketController extends TextWebSocketHandler {

    @Autowired
    private UserService userService;

    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

/*    private SimpMessagingTemplate template;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }*/

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
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

        for(WebSocketSession wss : sessions) {
            try {
                wss.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessions.remove(session);
    }

    @RequestMapping(value = "/chat")
    public void toClient() {

    }
}
