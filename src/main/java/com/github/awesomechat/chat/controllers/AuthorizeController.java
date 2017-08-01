package com.github.awesomechat.chat.controllers;

import com.github.awesomechat.chat.models.User;
import com.github.awesomechat.chat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;


@Controller
public class AuthorizeController {

    private UserService userService;
    private ServletContextListener cxtListener;

    @Autowired
    AuthorizeController(ServletContextListener listener, UserService service) {
        cxtListener = listener;
        userService = service;
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public ModelAndView authorize(HttpServletRequest request) {
        String userFirstName = request.getParameter("firstName");
        String userLastName = request.getParameter("lastName");
        String userPassword = request.getParameter("password");
        User user = new User(userFirstName, userLastName, userPassword);
        if(userService.isValid(user)) {
            return new ModelAndView("redirect:/chat");
        } else {
            return new ModelAndView("redirect:/error_page");
        }
    }

    @RequestMapping("/error_page")
    public String errorHandling(HttpServletRequest request) {
        System.out.println(request.getAttribute("xxxx"));
        return "error_page";
    }
}
