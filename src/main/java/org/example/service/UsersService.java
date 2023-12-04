package org.example.service;

import org.example.dtos.AddUserDto;
import org.example.dtos.ShowModelInfoDto;
import org.example.dtos.ShowUserInfoDto;
import org.example.dtos.UsersDto;
import org.example.models.Models;
import org.example.models.Users;

import java.util.List;

public interface UsersService {
    List<UsersDto> getAllUsers();

    UsersDto registerUser(UsersDto usersDto);
//    void deleteUser(String userID);

    UsersDto updateUser(String userID, UsersDto updateUser);

    List<Models> findModelsByUserName(String userName);

    List<UsersDto> findUsersByRole(int role);
    void addUser(AddUserDto userModel);

    List<ShowUserInfoDto> allUsers();

    ShowUserInfoDto userDetails(String userName);

    void removeUser(String userName);
    Users getUserById(String modelId);

}
