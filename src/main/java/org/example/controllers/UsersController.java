package org.example.controllers;

import org.example.dtos.UsersDto;
import org.example.models.Model;
import org.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/")
    Iterable<UsersDto> all() {
        return usersService.getAllUsers();
    }

    @PostMapping("/")
    UsersDto newUser(@RequestBody UsersDto newUser) {
        return usersService.registerUser(newUser);
    }

    @DeleteMapping("/{userID}")
    void deleteUser(@PathVariable("userID") UsersDto usersDto) {
        usersService.registerUser(usersDto);
    }

    @PutMapping("/{userID}")
    public UsersDto updateUser(@PathVariable("userID") String userID, @RequestBody UsersDto updateUser) {
        return usersService.updateUser(userID, updateUser);
    }
}
