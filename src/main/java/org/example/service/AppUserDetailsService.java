package org.example.service;

import org.example.models.Role;
import org.example.models.Users;
import org.example.repo.UsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class AppUserDetailsService implements UserDetailsService {
    private UsersRepository userRepository;

    public AppUserDetailsService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .map(u -> {
                    Role role = u.getRoles();
                    if (role != null) {
                        return new User(
                                u.getUserName(),
                                u.getPassword(),
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                        );
                    } else {
                        throw new UsernameNotFoundException(username + " has no roles!");
                    }
                })
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
