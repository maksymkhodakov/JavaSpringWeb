package com.example.sweater.controller;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
       User userFromDb = userRepo.findByUserName(user.getUsername());

       if (userFromDb != null){
            model.put("message", "User exists!");
            return "registration";
       }

       user.setActive(true);
       user.setRoles(Collections.singleton(Role.USER));
       userRepo.save(user);

        return "redirect:/login";
    }
}
