package com.vang.admin_service.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admins, String> {

    @Query(value = "select count(ad.email) from admins ad where ad.email = ?1", nativeQuery = true)
    long countByEmail(String email);

    @Query(value = "select count(ad.username) from admins ad where ad.username = ?1", nativeQuery = true)
    long countByUsername(String username);

    @Query(value = "select count(ad.email) from admins ad where ad.email = ?1 and ad.email != ?2", nativeQuery = true)
    long countByEmailAndOldEmail(String email, String oldEmail);

    @Query(value = "select ad.password from admins ad where ad.username = ?1 and ad.status = 1", nativeQuery = true)
    String getPasswordByUsername(String username);

}