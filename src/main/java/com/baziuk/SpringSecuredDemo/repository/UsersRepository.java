package com.baziuk.SpringSecuredDemo.repository;

import com.baziuk.SpringSecuredDemo.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    @Query(value = "SELECT * FROM users WHERE name = ?", nativeQuery = true)
    Optional<UsersEntity> findByUserName(String username);
}
