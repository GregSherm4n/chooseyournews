package com.webserver.chooseyournews.controller;

import com.webserver.chooseyournews.dto.UserForm;
import com.webserver.chooseyournews.dto.BlogForm;
import com.webserver.chooseyournews.model.Blog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class BlogController {
    private CopyOnWriteArrayList<Blog> blogStorage = new CopyOnWriteArrayList<>() {{
        add(new Blog("admin", "Здесь создаем блоги", "Каждый" +
                " добавленный блог представляет собой класс, состоящий из переменных: автор, " +
                "тема, текст и дата создания блога. После отправки блога на сервер, вас перенаправит " +
                "на авторизацию", 1604275200000L));
        add(new Blog("egor", "London is the capital of GB", "London is the capital " +
                "of Great Britain, its political, economic and commercial centre. It is one of the largest" +
                "cities in the world and the largest city in Europe. Its population is about 8 million." +
                "London is situated on the river Thames. The city is very old. " +
                "It has more than 20 centuries old history. Traditionally" +
                "it is divided into several parts, the City, Westminster," +
                "the West End and the East. End. They are very different from each other.", 1604412372000L));
    }};

    @GetMapping("/blogs")
    public String blogsPage(Model model){
//        var blogs = new ModelAndView();
//        blogs.setViewName("/blogs");
//        blomgs.addObject("blogList", blogStorage);
        model.addAttribute("blogList", blogStorage);
        return "/blogs";
    }


    @GetMapping("/bloging/{login}")
    public String addBlogPage(Model model, @PathVariable("login") String login, @RequestParam("password") String password) {
        model.addAttribute("request", new UserForm(login, password));
        model.addAttribute("form", new BlogForm());
        return "bloging";
    }

    @PostMapping("/bloging/{login}")
    private String addBlogHandlerPage(Model model, @PathVariable("login") String login, @RequestParam("password") String password, @ModelAttribute BlogForm form) {
        blogStorage.add(new Blog(login, form.getTitle(), form.getContent(), System.currentTimeMillis()));
        System.out.println("Успешно добавлен новый пост кем:" + login);
        model.addAttribute("form", new BlogForm());
        return "redirect:/authorization";
    }

}
