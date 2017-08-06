package com.github.awesomechat.chat.controllers;

import com.github.awesomechat.chat.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ChatController {

    @RequestMapping(value = "/chat")
    public ModelAndView toClient(@ModelAttribute("user") User user) {
        if (user.getFullName().equals("null null") ) return new ModelAndView("redirect:/login");
        return new ModelAndView("chat", "user", user);
    }
}
