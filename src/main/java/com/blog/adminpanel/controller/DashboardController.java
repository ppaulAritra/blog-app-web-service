package com.blog.adminpanel.controller;

import com.blog.adminpanel.domain.dto.BloggerDTO;
import com.blog.adminpanel.service.implementation.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DashboardController {
    BloggerService bloggerService;
    @Autowired
    public DashboardController(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login()
    {
        ModelAndView mv = new ModelAndView("login");
        BloggerDTO blogger= new BloggerDTO();
        mv.addObject("blogger",blogger);
        return mv;
    }
	@RequestMapping(value={"/", "/home"},method = RequestMethod.GET)
    public ModelAndView  home() {
    	
        ModelAndView mv = new ModelAndView("dashboard");
        
        
        return mv;
    }
    @PostMapping("/registration")
    public BloggerDTO registerBlogger(BloggerDTO bloggerDTO){
        return bloggerService.registerBlogger(bloggerDTO);
    }

}
