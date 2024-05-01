package org.boot.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("hello");
        modelAndView.addObject("greeting", "Hello 타임리프.^^");

        return modelAndView;
    }
}