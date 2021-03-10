package com.aitu.votingsystem.controller;

import com.aitu.votingsystem.model.User;
import com.aitu.votingsystem.repository.ResultRepository;
import com.aitu.votingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private ResultRepository resultRepository;

    @GetMapping("/profile")
    public String profilePage(Principal principal, Model model) {
        User user = userRepository.getUserByUsername(principal.getName());
        model.addAttribute("user", user);

        List<String> titles = resultRepository.getSurveyTitleByUserId(user.getId());
        List<String> descriptions = resultRepository.getSurveyDescriptionByUserId(user.getId());


        model.addAttribute("titles", titles);
        model.addAttribute("descriptions", descriptions);

        return "profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(Principal principal, Model model, @ModelAttribute("firstName") String firstName,
                                @ModelAttribute("lastName") String lastName, @ModelAttribute("age") int age) {
        User user = userRepository.getUserByUsername(principal.getName());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        userRepository.save(user);
        model.addAttribute("user", user);
        model.addAttribute("success_updated_profile", "User Profile updated successfully");
        return "settings";
    }

    @GetMapping("/settings")
    public String settings(Principal principal, Model model) {
        User user = userRepository.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "settings";
    }

    @PostMapping("/updatePassword")
    public String updatePassword(Principal principal, Model model,
                                 @ModelAttribute("password") String password,
                                 @ModelAttribute("newPassword") String newPassword,
                                 @ModelAttribute("confirmPassword") String confirmPassword) {
        User user = userRepository.getUserByUsername(principal.getName());
        if (encoder.matches(password, user.getPassword())) {
            if (encoder.matches(newPassword, user.getPassword())) {
                model.addAttribute("error", "Your new password should be different");
            } else if (newPassword.equals(confirmPassword)) {
                user.setPassword(encoder.encode(newPassword));
                userRepository.save(user);
                model.addAttribute("success", "Password updated successfully!");
            } else {
                model.addAttribute("error", "Passwords do not match");
            }
        } else {
            model.addAttribute("error", "Current password incorrect");
        }
        model.addAttribute("user", user);
        return "settings";
    }
}
