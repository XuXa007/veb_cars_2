package org.example.repo;

import org.apache.catalina.User;
import org.example.models.Model;
import org.example.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, String> {
    @Query("SELECT u FROM Users u JOIN u.role r WHERE r.roleEnum = :role")
    List<Users> findUsersByRole(@Param("role") int role);
}