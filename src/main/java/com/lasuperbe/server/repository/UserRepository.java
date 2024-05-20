package com.lasuperbe.server.repository;

import com.lasuperbe.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM user u WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);

    List<User> findByEmail(String email);
}
