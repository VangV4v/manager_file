package com.vang.oauth_service.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, String> {

    @Query(value = "select count(u.username) from users u where u.username =?1 and u.email= ?2 and u.type = 1", nativeQuery = true)
    long countByUsernameAndEmail(String username, String email);
}