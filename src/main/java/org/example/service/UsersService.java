package org.example.service;

import jakarta.validation.ConstraintViolation;
import org.example.dtos.AddUserDto;
import org.example.dtos.ShowUserInfoDto;
import org.example.dtos.UsersDto;
import org.example.exception.NotFoundException;
import org.example.models.*;
import org.example.repo.UsersRepository;
import org.example.utils.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<UsersDto> getAllUsers() {
        return usersRepository.findAll().stream().map((s) -> modelMapper.map(s, UsersDto.class)).collect(Collectors.toList());
    }

    public UsersDto registerUser(UsersDto users) {

        if(!this.validationUtil.isValid(users)) {
            this.validationUtil
                    .violations(users)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            throw new IllegalArgumentException("Illegal arguments in Users!");
        }

        Users u = modelMapper.map(users, Users.class);
        String userId = u.getId();
        if (u.getId() == null || usersRepository.findById(userId).isEmpty()) {
            return modelMapper.map(usersRepository.save(u), UsersDto.class);
        } else {
            throw new NotFoundException("A user with this id already exists");
        }
    }


    public UsersDto updateUser(String userID, UsersDto updateUser) {
        Users existingUser = usersRepository.findById(userID).orElseThrow(() -> new NotFoundException("Could not find" + user + " by id: " + userID));

        Role existingRole = modelMapper.map(updateUser.getRole(), Role.class);

        existingUser.setRole(existingRole);

//        existingRole.setId(updateUser.getId());
        existingUser.setUserName(updateUser.getUserName());
        existingUser.setPassword(updateUser.getPassword());
        existingUser.setFirstName(updateUser.getFirstName());
        existingUser.setLastName(updateUser.getLastName());
        existingUser.setActive(updateUser.isActive());
        existingUser.setImageURL(updateUser.getImageURL());
        existingUser.setCreated(updateUser.getCreated());
        existingUser.setModified(updateUser.getModified());

        Users savedUser = usersRepository.save(existingUser);
        return modelMapper.map(savedUser, UsersDto.class);
    }

    public List<Models> findModelsByUserName(String userName) {
        return null;
    }

    public List<UsersDto> findUsersByRole(int role) {
        return usersRepository.findUsersByRole(role).stream().map((s) -> modelMapper.map(s, UsersDto.class)).collect(Collectors.toList());
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

    public Users getUserById(String modelId) {
        return usersRepository.findById(modelId).orElse(null);

    }

    public List<ShowUserInfoDto> getAll() {
        return usersRepository.findAll().stream().map((user) -> modelMapper.map(user, ShowUserInfoDto.class)).collect(Collectors.toList());
    }
}
