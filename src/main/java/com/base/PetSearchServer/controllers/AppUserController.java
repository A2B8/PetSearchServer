package com.base.PetSearchServer.controllers;

import com.base.PetSearchServer.entity.AppUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AppUserController {
    @GetMapping("/{userId}")
    public AppUser getUser(@PathVariable("userId") int userId) {
        return null;
    }
}
