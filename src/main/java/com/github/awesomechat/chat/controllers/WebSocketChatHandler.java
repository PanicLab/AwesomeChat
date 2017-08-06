package com.github.awesomechat.chat.controllers;


import com.github.awesomechat.chat.messages.*;
import com.github.awesomechat.chat.models.ChatBotBean;
import com.github.awesomechat.chat.services.JsonDecoder;
import com.github.awesomechat.chat.services.JsonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Controller
public class WebSocketChatHandler extends TextWebSocketHandler {

    @Autowired
    private JsonEncoder encoder;
    @Autowired
    private JsonDecoder decoder;
    @Autowired
    private TaskExecutor executor;
    @Autowired
    private ChatBotBean botBean;
    private static final Object broadcastLock = new Object();

    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private static final List<String> users = Collections.synchronizedList(new ArrayList<>());
    private static final String BOT = "George Raspupkin";


    static {
        users.add(BOT);
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String json = message.getPayload();
        parseMessage(json, session);

    }

    private void parseMessage(String json, WebSocketSession session) {
        Message message = decoder.decode(json);
        if (message instanceof JoinMessage) {
            process(JoinMessage.class.cast(message), session);
            broadcast(json);

            UserListMessage userList = new UserListMessage(users);
            json = encoder.encode(userList);
            broadcast(json);
            return;
        }

        if (message instanceof ChatMessage) {
            String author = ChatMessage.class.cast(message).getAuthor();
            String textIn = ChatMessage.class.cast(message).getMessage();
            String textOut = timeNow() + "[" + author + "] " + textIn;

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setAuthor(author);
            chatMessage.setMessage(textOut);
            json = encoder.encode(chatMessage);
            broadcast(json);

            if(textIn.contains(BOT)) {
                executor.execute( () -> {
                    String botMessage = botBean.response(textIn, author);
                    botMessage = timeNow() + "[" + BOT + "] " + author + ", " + botMessage;
                    ChatMessage msg = new ChatMessage();
                    msg.setAuthor(BOT);
                    msg.setMessage(botMessage);
                    String jsn = encoder.encode(msg);
                    broadcast(jsn);
                });
            }
            return;
        }

        String unknown = "Unknown message type detected!";
        broadcast(unknown);
    }

    private void broadcastUserList() {
        UserListMessage userList = new UserListMessage(users);
        String json = encoder.encode(userList);
        broadcast(json);
    }

    private void process(JoinMessage message, WebSocketSession session) {
        users.add(message.getName());
        ChatBotBean.addUser(message.getName());
        session.getAttributes().put("userName", message.getName());
    }

    private void process(QuitMessage message) {
        users.remove(message.getName());
    }


    private void broadcast(String json) {
        synchronized (broadcastLock) {
            TextMessage message = new TextMessage(json);
            for (WebSocketSession wss : sessions) {
                try {
                    wss.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String timeNow() {
        LocalTime time = LocalTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(format) + ": ";
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);

        String userName = (String)session.getAttributes().get("userName");
        QuitMessage quitMessage = new QuitMessage();
        quitMessage.setName(userName);

        process(quitMessage);

        String json = encoder.encode(quitMessage);
        broadcast(json);

        broadcastUserList();
    }


}
