package com.github.awesomechat.chat.controllers;


import com.github.awesomechat.chat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Controller
public class WebSocketController extends TextWebSocketHandler {

    @Autowired
    private UserService userService;

/*    private SimpMessagingTemplate template;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }*/


    @RequestMapping(value = "/chat")
    public void toClient() {

    }
}
