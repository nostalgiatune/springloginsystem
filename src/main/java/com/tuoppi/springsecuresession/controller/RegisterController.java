package com.tuoppi.springsecuresession.controller;

import com.tuoppi.springsecuresession.model.StatefulUserService;
import com.tuoppi.springsecuresession.service.UserManager;
import com.tuoppi.springsecuresession.user.Authority;
import com.tuoppi.springsecuresession.user.UserProfile;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/")
public class RegisterController {
    
    @Autowired
    private UserManager userManager;
    
    @Autowired
    private StatefulUserService activeSession;
    
    @RequestMapping(method = GET)
    public String index() {
        return "index";
    }
    
    @RequestMapping(value = "/init", method = GET)
    public String init() {
        userManager.add(new UserProfile("user", "user", new Authority("ROLE_USER")));
        userManager.add(new UserProfile("admin", "admin",
            new Authority("ROLE_ADMIN"),
            new Authority("ROLE_USER")
        ));
        return "redirect:users";
    }
    
    @RequestMapping(value = "/users", method = GET)
    public @ResponseBody String users() {
        return userManager.findAll().toString();
    }
    
    @RequestMapping(value = "/admin", method = GET)
    public @ResponseBody String admin() {
        return "This page requires ROLE_ADMIN authorization";
    }
    
    @RequestMapping(value = "/session", method = GET)
    public String session(Model model) {
        
        model.addAttribute("user", "I am " + activeSession.getActiveUsername()
         + " and my personal data is " + activeSession.getActiveUserPersonalData());
        
        model.addAttribute("users", activeSession.getUserProfiles());
        return "session";
    }
    
    @RequestMapping(value = "/register", method = GET)
    public String register(@ModelAttribute("user") UserProfile user) {
        return "register";
    }
    
    @RequestMapping(value = "/register", method = POST)
    public String register(@ModelAttribute("user") @Valid UserProfile user,
            BindingResult errors, RedirectAttributes redirectAttributes) {
        
        if (errors.hasErrors() || userManager.exist(user))
            return "register";
        
        userManager.add(user);
        
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:registered";
    }
    
    @RequestMapping(value = "/registered", method = GET)
    public @ResponseBody String registered(Model model) {
        
        UserProfile user = (UserProfile) model.asMap().get("user");
        return user.toString();
    }
    
}