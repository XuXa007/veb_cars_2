package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dtos.AddUserDto;
import org.example.dtos.ShowUserInfoDto;
import org.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/")
    Iterable<ShowUserInfoDto> all() {
        return usersService.getAllUsers();
    }

    @PostMapping("/add")
    public String addUser(@Valid AddUserDto userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);
            return "redirect:/user/add";
        }

        usersService.addUser(userModel);

        return "redirect:/user/all";
    }

    @GetMapping("/add")
    public String addUser() {
        return "user-add";
    }

    @ModelAttribute("userModel")
    public AddUserDto initUser() {
        return new AddUserDto();
    }

    @GetMapping("/all")
    public String showAllUser(Model model) {
        model.addAttribute("infoUsers", usersService.allUsers());

        return "user-all";
    }

    @GetMapping("/user-details/{user-name}")
    public String UserDetails(@PathVariable("user-name") String userName, Model model) {
        model.addAttribute("userDetails", usersService.userDetails(userName));

        return "user-details";
    }

    @GetMapping("/user-delete/{user-name}")
    public String deleteUser(@PathVariable("user-name") String userName) {
        usersService.removeUser(userName);

        return "redirect:/user/all";
    }
}
