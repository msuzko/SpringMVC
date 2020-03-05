package com.mec.spring.controller;

import com.mec.spring.objects.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;


@Controller
public class LoginController {

    public static final String LOGIN = "login";
    public static final String MAIN = "main";

    @Autowired
    private MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(@ModelAttribute User user, Locale locale) {
        System.out.println(locale.getDisplayLanguage());
        System.out.println(
                messageSource.getMessage("locale", new String[]{locale.getDisplayName(locale)}, locale));
        user.setName("usernamevalue");
        return LOGIN;
    }

    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public ModelAndView checkUser(Locale locale, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("locale", messageSource.getMessage("locale", new String[]{locale.getDisplayName(locale)}, locale));
        if (bindingResult.hasErrors())
            modelAndView.setViewName(LOGIN);
        else
            modelAndView.setViewName(MAIN);
        return modelAndView;
    }


    @RequestMapping(value = "/failed", method = RequestMethod.GET)
    public ModelAndView failed() {
        return new ModelAndView("login-failed", "message", "Login failed!");
    }

    @RequestMapping(value = "/get-json-user/{name}/{admin}", method = RequestMethod.GET, produces = "application/xml")
    @ResponseBody
    public User getUser(@PathVariable("name") String name, @PathVariable("admin") boolean admin) {
        User user = new User();
        user.setName(name);
        user.setAdmin(admin);
        return user;
    }

    @RequestMapping(value = "/put-json-user", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> setJsonUser(@RequestBody User user) {
        logger.info(user.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
