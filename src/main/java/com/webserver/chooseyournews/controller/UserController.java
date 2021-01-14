package com.webserver.chooseyournews.controller;

import com.webserver.chooseyournews.dto.UserForm;
import com.webserver.chooseyournews.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class UserController {

    private ConcurrentHashMap<String, User> usersStorage = new ConcurrentHashMap<>() {{
        put("admin", new User("admin", "admin", User.DEFAULT_AVATAR));
        put("Greg", new User("Greg", "12345", User.DEFAULT_AVATAR));
        put("Misha", new User("Misha", "12345", User.DEFAULT_AVATAR));
        put("Egor", new User("Egor", "12345", User.DEFAULT_AVATAR));
    }};

    @GetMapping("/authorization")
    public String authorizationPage(Model model) {
        model.addAttribute("form", new UserForm());
        return "authorization";
    }

    @PostMapping("/authorization")
    public String authorizationHandlerPage(Model model, @ModelAttribute UserForm form) {
        if (!usersStorage.containsKey(form.getLogin()) || !form.getPassword().equals(usersStorage.get(form.getLogin()).getPassword())) {
            model.addAttribute("error", true);
            model.addAttribute("form", new UserForm());
            return "authorization";
        } else {
            System.out.println("На сайт вошёл: " + form.getLogin());
            model.addAttribute("form", new UserForm());
            return "redirect:/bloging/" + form.getLogin() + "/?password=" + form.getPassword();
        }
    }

    @GetMapping("/registration")
    public String registrationPage(Model model){
        model.addAttribute("form", new UserForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationHandlerPage(Model model, @ModelAttribute UserForm form){
        if (usersStorage.containsKey(form.getLogin())){
            model.addAttribute("error", true);
            model.addAttribute("form", new UserForm());
            return "registration";
        } else {
            User user = new User(form.getLogin(), form.getPassword(), User.DEFAULT_AVATAR);
            usersStorage.put(form.getLogin(), user);
            System.out.println("На сайт добавлен новый пользователь под ником: " + form.getLogin());
            return "redirect:/home";
        }
    }

}
