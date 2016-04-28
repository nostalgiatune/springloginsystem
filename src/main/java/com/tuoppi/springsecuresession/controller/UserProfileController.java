package com.tuoppi.springsecuresession.controller;

import com.tuoppi.springsecuresession.service.UserManager;
import com.tuoppi.springsecuresession.user.UserProfile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserProfileController {
    
    @Autowired
    private UserManager userManager;
    
    @RequestMapping(value = "/settings", method = GET)
    public String settings(@AuthenticationPrincipal User user, Model model) {
        
        UserProfile activeUser = userManager.find(user.getUsername());
        model.addAttribute("user", activeUser);
        return "settings";
    }
    
    @RequestMapping(value = "/settings", method = POST)
    public String processSettings(@ModelAttribute("user") UserProfile profile,
            BindingResult errors) {
        
        if (errors.hasErrors())
            return "settings";
        
        userManager.update(profile);
        return "redirect:/";
    }
    
    @RequestMapping(value = "/all", method = GET)
    public String all(Model model) {
        List<UserProfile> profiles = userManager.findAll();
        model.addAttribute("profiles", profiles);
        return "all";
    }
    
}