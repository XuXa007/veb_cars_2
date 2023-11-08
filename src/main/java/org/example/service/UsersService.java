package org.example.service;

import org.example.dtos.UsersDto;
import org.example.models.Model;
import org.example.models.Users;

import java.util.List;
import java.util.UUID;

public interface UsersService {
    List<UsersDto> getAllUsers();

    UsersDto registerUser(UsersDto usersDto);
    void deleteUser(String userID);

    UsersDto updateUser(String userID, UsersDto updateUser);

    List<Model> findModelsByUserName(String userName);

    List<UsersDto> findUsersByRole(int role);
}
