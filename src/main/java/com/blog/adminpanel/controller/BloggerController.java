/**
 *
 */
package com.blog.adminpanel.controller;

import java.util.ArrayList;
import java.util.List;

import com.blog.adminpanel.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.blog.adminpanel.domain.Image;
import com.blog.adminpanel.domain.dto.BloggerDTO;
import com.blog.adminpanel.oauth.SettingsRepository;
import com.blog.adminpanel.service.implementation.BloggerService;
import com.blog.adminpanel.service.implementation.RoleService;


/**
 * @author Aritra
 *
 */
@RestController
@RequestMapping("/blogger")
public class BloggerController {

    private BloggerService bloggerService;
    private RoleService roleService;
    private SettingsRepository settings;

    @Autowired
    public BloggerController(BloggerService bloggerService, RoleService roleService, SettingsRepository settings) {
        this.bloggerService = bloggerService;
        this.roleService = roleService;
        this.settings = settings;
    }

    @GetMapping("/view")
    public ModelAndView viewBlogger() {
        ModelAndView modelandview = new ModelAndView("blogger/view");

        List<Role> roles = roleService.getAllRoles();
        List<BloggerDTO> bloggerList = new ArrayList<>();
        modelandview.addObject("roleList", roles);
        modelandview.addObject("bloggerList", bloggerList);
        modelandview.addObject("userId", settings.getUserId());
        return modelandview;
    }

    @GetMapping("/role/type")
    public ModelAndView getBloggerByRoleAndType(@RequestParam("roleId") Long roleId, @RequestParam("type") String type) {
        ModelAndView modelandview = new ModelAndView("blogger/view ::blogger-list");
        List<BloggerDTO> bloggerList = bloggerService.getBloggerByRoleAndStatus(roleId, type);

        modelandview.addObject("bloggerList", bloggerList);
        return modelandview;
    }

    @PostMapping("/delete")
    public String deleteBlogger(@RequestParam Long bloggerId) throws Exception {
        return bloggerService.deleteBlogger(bloggerId, settings.getUserId());
    }

    @PostMapping("/activate")
    public BloggerDTO approveBlogger(@RequestParam Long bloggerId) {
        return bloggerService.approveBlogger(bloggerId);
    }

    @GetMapping("/add")
    public ModelAndView createBlogger() {
        ModelAndView modelandview = new ModelAndView("blogger/create");
        List<Role> roles = roleService.getAllRoles();
        BloggerDTO blogger = new BloggerDTO();
        modelandview.addObject("roleList", roles);
        modelandview.addObject("blogger", blogger);
        return modelandview;

    }

    @PostMapping("/create")

    public ModelAndView createEmployee(BloggerDTO blogger) {

        bloggerService.createBlogger(blogger);
        String redirectUrl = "/blogger/view";
        return new ModelAndView("redirect:" + redirectUrl);
    }


}
