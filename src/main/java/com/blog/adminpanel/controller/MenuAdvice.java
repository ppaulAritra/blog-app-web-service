package com.blog.adminpanel.controller;

import com.blog.adminpanel.oauth.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Aritra Paul
 * @created_on 8/29/20 at 3:44 PM
 * @project adminpanel
 */
@ControllerAdvice
public class MenuAdvice {
    @Autowired
    SettingsRepository settingsRepository;

    @ModelAttribute
    public void addAttributes(Model model) {

        model.addAttribute("roleName", settingsRepository.getRoleName());
        model.addAttribute("bloggerId", settingsRepository.getUserId());

    }
}
