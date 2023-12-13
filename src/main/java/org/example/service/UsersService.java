package org.example.service;

import jakarta.validation.ConstraintViolation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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


    @Cacheable("user")
    public List<ShowUserInfoDto> getAllUsers() {
        return usersRepository.findAll().stream().map((s) -> modelMapper.map(s, ShowUserInfoDto.class)).collect(Collectors.toList());
    }

    @CacheEvict(cacheNames = "user", allEntries = true)
    public void addUser(AddUserDto userModel) {
        userModel.setCreated(LocalDateTime.now());
        userModel.setModified(LocalDateTime.now());
        Users users = modelMapper.map(userModel, Users.class);
        usersRepository.saveAndFlush(users);
    }

    @Cacheable("user")
    public List<ShowUserInfoDto> allUsers() {
        return usersRepository.findAll().stream().map(model -> modelMapper.map(model, ShowUserInfoDto.class))
                .collect(Collectors.toList());
    }

    @Cacheable("user")
    public ShowUserInfoDto userDetails(String userName) {
        return modelMapper.map(usersRepository.findByUserName(userName).orElse(null), ShowUserInfoDto.class);
    }

    @CacheEvict(cacheNames = "user", allEntries = true)
    public void removeUser(String userName) {
        usersRepository.deleteByUserName(userName);
    }

    @Cacheable("user")
    public List<ShowUserInfoDto> getAll() {
        return usersRepository.findAll().stream().map((users) -> modelMapper.map(users, ShowUserInfoDto.class)).collect(Collectors.toList());
    }

    @Cacheable("user")
    public List<ShowUserInfoDto> getAllUsersForOffer() {
        return usersRepository.findAll().stream().map((users) -> modelMapper.map(users, ShowUserInfoDto.class)).collect(Collectors.toList());
    }

}
