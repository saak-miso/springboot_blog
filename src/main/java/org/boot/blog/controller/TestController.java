package org.boot.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void hello(Model model) {

        System.out.println("???????????????");

        model.addAttribute("greeting", "Hello 타임리프.^^");
    }
}