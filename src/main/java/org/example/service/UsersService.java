package org.example.service;

import jakarta.validation.ConstraintViolation;
import org.example.dtos.AddUserDto;
import org.example.dtos.ShowBrandInfoDto;
import org.example.dtos.ShowModelInfoDto;
import org.example.dtos.ShowUserInfoDto;
import org.example.exception.NotFoundException;
import org.example.models.*;
import org.example.repo.UsersRepository;
import org.example.utils.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    private String user = "user";

    public List<ShowUserInfoDto> getAllUsers() {
        return usersRepository.findAll().stream().map((s) -> modelMapper.map(s, ShowUserInfoDto.class)).collect(Collectors.toList());
    }


    public void addUser(AddUserDto userModel) {
        userModel.setCreated(LocalDateTime.now());
        userModel.setModified(LocalDateTime.now());
        userModel.setImageURL("ooopss...");
        Users users = modelMapper.map(userModel, Users.class);
        usersRepository.saveAndFlush(users);
    }

    public List<ShowUserInfoDto> allUsers() {
        return usersRepository.findAll().stream().map(model -> modelMapper.map(model, ShowUserInfoDto.class))
                .collect(Collectors.toList());
    }

    public ShowUserInfoDto userDetails(String userName) {
        return modelMapper.map(usersRepository.findByUserName(userName).orElse(null), ShowUserInfoDto.class);
    }

    public void removeUser(String userName) {
        usersRepository.deleteByUserName(userName);
    }


    public List<ShowUserInfoDto> getAll() {
        return usersRepository.findAll().stream().map((users) -> modelMapper.map(users, ShowUserInfoDto.class)).collect(Collectors.toList());
    }

    public List<ShowUserInfoDto> getAllUsersForOffer() {
        return usersRepository.findAll().stream().map((users) -> modelMapper.map(users, ShowUserInfoDto.class)).collect(Collectors.toList());
    }

}
