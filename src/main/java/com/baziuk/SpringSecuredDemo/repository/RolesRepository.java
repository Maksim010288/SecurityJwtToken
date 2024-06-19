package com.baziuk.SpringSecuredDemo.repository;

import com.baziuk.SpringSecuredDemo.entity.RolesEntity;
import com.baziuk.SpringSecuredDemo.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    @Query(value = "SELECT * FROM roles WHERE name = ?", nativeQuery = true)
    List<UsersEntity> findByUserName(String username);
}
