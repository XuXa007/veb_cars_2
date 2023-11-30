package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.AddModelDto;
import org.example.dtos.AddOfferDto;
import org.example.dtos.AddUserDto;
import org.example.dtos.UsersDto;
import org.example.models.Model;
import org.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/")
    Iterable<UsersDto> all() {
        return usersService.getAllUsers();
    }

    @PostMapping("/add")
    public String addUser(@Valid AddUserDto userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);
            return "redirect:/users/add";
        }

        usersService.addUser(userModel);

        return "redirect:/users/all";
    }

    @GetMapping("/add")
    public String addUser() {
        return "user-add";
    }

    @ModelAttribute("userModel")
    public AddUserDto initUser() {
        return new AddUserDto();
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
