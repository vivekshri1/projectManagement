package com.jrp.pma.controllers;

import com.jrp.pma.entities.UserAccount;
import com.jrp.pma.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    SecurityService securityService;

    @GetMapping("/register")
    public String register(Model model){
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);
        return "security/register";
    }

    @PostMapping("/register/save")
    public String saveUser(Model model, UserAccount userAccount){
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
        securityService.saveUser(userAccount);
        return "redirect:/";
    }
}
