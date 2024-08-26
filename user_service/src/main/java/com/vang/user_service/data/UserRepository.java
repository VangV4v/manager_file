package com.vang.user_service.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, String> {

    @Query(value = "select count(u.username) from users u where u.username = ?1", nativeQuery = true)
    long countByUsername(String username);

    @Query(value = "select count(u.email) from users u where u.email = ?1", nativeQuery = true)
    long countByEmail(String email);

    @Query(value = "select u.password from users u where u.username = ?1 and u.status = 1", nativeQuery = true)
    String getPasswordByUsername(String username);
}