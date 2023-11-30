package org.example.repo;

import org.example.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    @Query("SELECT u FROM Users u JOIN u.role r WHERE r.roleEnum = :role")
    List<Users> findUsersByRole(@Param("role") int role);
}