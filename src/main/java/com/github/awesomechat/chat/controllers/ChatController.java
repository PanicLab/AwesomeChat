package com.github.awesomechat.chat.controllers;

import com.github.awesomechat.chat.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChatController {

    @RequestMapping(value = "/chat")
    public ModelAndView toClient(@ModelAttribute("user") User user) {
/*        User user = new User();
        user.setFirstName("Vasja");
        user.setLastName("Pupkin");*/

        return new ModelAndView("chat", "user", user);
    }
}
