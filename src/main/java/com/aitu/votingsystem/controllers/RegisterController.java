package com.aitu.votingsystem.controllers;

import com.aitu.votingsystem.model.User;
import com.aitu.votingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model, Principal principal) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        System.out.println("I am here!");
        System.out.println(user.toString());
        if (userRepository.getUserByUsername(user.getUsername()) != null){
            model.addAttribute("message", "Username is already taken");
            return "registration";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "registration";
    }
}
