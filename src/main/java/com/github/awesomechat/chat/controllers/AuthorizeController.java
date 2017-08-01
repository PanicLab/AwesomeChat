package com.github.awesomechat.chat.controllers;

import com.github.awesomechat.chat.Message;
import com.github.awesomechat.chat.ServletCxtListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.WebContext;


import javax.servlet.http.HttpServletRequest;


@Controller
public class AuthorizeController {

    @Autowired
    ServletCxtListener cxtListener;

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public ModelAndView authorize(HttpServletRequest request, Model model, RedirectAttributes redirectAttrs) {
        final String LOGIN_ERROR_MESSAGE = "This is login error message";
        model.addAttribute("xxxx", LOGIN_ERROR_MESSAGE);
        redirectAttrs.addAttribute("xxxx", LOGIN_ERROR_MESSAGE);

        return new ModelAndView("redirect:/error_page");
    }

    @RequestMapping("/error_page")
    public String errorHandling(HttpServletRequest request) {
        System.out.println(request.getAttribute("xxxx"));
        return "error_page";
    }
}
