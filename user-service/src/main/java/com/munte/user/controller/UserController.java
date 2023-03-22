package com.munte.user.controller;

import com.munte.user.entity.AppUser;
import com.munte.user.service.UserService;
import com.munte.user.valueobject.ResponseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public AppUser saveUser(@RequestBody AppUser user){
        log.info("Inside of saveUser from UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplate getUserWithDepartment(@PathVariable("id") Long userId){
        log.info("Inside of getUserWithDepartment from UserController");
        return userService.getUserWithDepartment(userId);
    }

}
