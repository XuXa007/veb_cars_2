package org.example.init;

import org.example.Enums.RoleEnum;
import org.example.models.Role;
import org.example.models.Users;
import org.example.repo.UserRoleRepository;
import org.example.repo.UsersRepository;
import org.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Init implements CommandLineRunner {
    private final UsersRepository userRepository;

    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String defaultPassword;

    public Init(UsersRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, @Value("${app.default.password}") String defaultPassword) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultPassword = defaultPassword;
    }

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var moderatorRole = new Role(RoleEnum.Moderator);
            var adminRole = new Role(RoleEnum.Admin);
            var normalUserRole = new Role(RoleEnum.User);
            userRoleRepository.save(moderatorRole);
            userRoleRepository.save(adminRole);
            userRoleRepository.save(normalUserRole);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
            initModerator();
            initNormalUser();
        }
    }

    private void initAdmin(){
        var adminRole = userRoleRepository.
                findRoleByName(RoleEnum.Admin).orElseThrow();

        var adminUser = new Users("admin", passwordEncoder.encode(defaultPassword), "admin@example.com", "Admin Adminovich", 30);
        adminUser.setRole(List.of(adminRole));

        userRepository.save(adminUser);
    }

    private void initModerator(){

        var moderatorRole = userRoleRepository.
                findRoleByName(RoleEnum.Moderator).orElseThrow();

        var moderatorUser = new Users("moderator", passwordEncoder.encode(defaultPassword), "moderator@example.com", "Moder Moderovich", 24);
        moderatorUser.setRole(List.of(moderatorRole));

        userRepository.save(moderatorUser);
    }

    private void initNormalUser(){
        var userRole = userRoleRepository.
                findRoleByName(RoleEnum.User).orElseThrow();

        var normalUser = new Users("user", passwordEncoder.encode(defaultPassword), "user@example.com", "User Userovich", 22);
        normalUser.setRole(List.of(userRole));

        userRepository.save(normalUser);
    }
}
