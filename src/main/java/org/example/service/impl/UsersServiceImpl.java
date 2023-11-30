package org.example.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.dtos.AddUserDto;
import org.example.dtos.UsersDto;
import org.example.exception.NotFoundException;
import org.example.models.*;
import org.example.repo.UsersRepository;
import org.example.service.UsersService;
import org.example.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    private String user = "user";

    @Override
    public List<UsersDto> getAllUsers() {
        return usersRepository.findAll().stream().map((s) -> modelMapper.map(s, UsersDto.class)).collect(Collectors.toList());
    }

    @Override
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

    @Override
    public void deleteUser(String userID) {
        Users user = usersRepository.findById(userID)
                .orElseThrow(() -> new NotFoundException("Could not find user by id: " + userID));
        usersRepository.delete(user);
    }

    @Override
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

    @Override
    public List<Model> findModelsByUserName(String userName) {
        return null;
    }

    @Override
    public List<UsersDto> findUsersByRole(int role) {
        return usersRepository.findUsersByRole(role).stream().map((s) -> modelMapper.map(s, UsersDto.class)).collect(Collectors.toList());
    }

    @Override
    public void addUser(AddUserDto userModel) {
        Users users = modelMapper.map(userModel, Users.class);
        usersRepository.saveAndFlush(users);
    }

//    @Autowired
//    public void setUserRepository(UsersRepository usersRepository) {
//        this.usersRepository = usersRepository;
//    }
}
