package com.vang.user_service.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, String> {

    @Query(value = "select count(u.username) from users u where u.username = ?1", nativeQuery = true)
    long countByUsername(String username);

    @Query(value = "select count(u.email) from users u where u.email = ?1", nativeQuery = true)
    long countByEmail(String email);

    @Query(value = "select u.password from users u where u.username = ?1 and u.status = 1", nativeQuery = true)
    String getPasswordByUsername(String username);

    @Query(value = "select u.userid,u.firstname,u.lastname,u.username,u.email,u.password,u.totalfile,u.totalfolder,u.type,u.status,u.createddate,u.lastmodified from users u where u.username = ?1 and status = 1", nativeQuery = true)
    Users findByUsername(String username);

    @Modifying
    @Query(value = "update users set password = ?2 where email = ?1", nativeQuery = true)
    int updatePasswordByEmail(String email, String password);
}