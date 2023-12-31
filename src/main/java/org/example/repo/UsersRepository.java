package org.example.repo;

import org.apache.catalina.User;
import org.example.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    @Query("SELECT u FROM Users u JOIN u.roles r WHERE r.name = :role")
    List<Users> findUsersByRole(@Param("role") int role);

    @Modifying
    @Transactional
    void deleteByUserName(String userName);

    Optional<Users> findUsersByUserName(String users);

    Optional<Users> findByUserName(String username);
    Optional<Users> findUserByEmail(String email);

}