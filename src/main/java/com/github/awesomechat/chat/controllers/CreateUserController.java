package com.github.awesomechat.chat.controllers;


import com.github.awesomechat.chat.models.User;
import com.github.awesomechat.chat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CreateUserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ModelAndView createUser(HttpServletRequest request, RedirectAttributes attributes) {
        String userFirstName = request.getParameter("firstName");
        String userLastName = request.getParameter("lastName");
        String userPassword = request.getParameter("password");
        User user = new User();
        user.setFirstName(userFirstName);
        user.setLastName(userLastName);
        if(userService.isExist(user)) {
            return new ModelAndView("redirect:/error_page");
        }

        userService.createUser(user, userPassword);

        ModelAndView mav = new ModelAndView("redirect:/chat");
        attributes.addFlashAttribute("user", user);
        return mav;
    }
}
