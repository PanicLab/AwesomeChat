package com.github.awesomechat.chat.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "index";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }
}