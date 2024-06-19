package com.baziuk.SpringSecuredDemo.repository;

import com.baziuk.SpringSecuredDemo.entity.RolesEntity;
import com.baziuk.SpringSecuredDemo.entity.UsersEntity;
import com.baziuk.SpringSecuredDemo.entity.UsersRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRolesRepository extends JpaRepository<UsersRolesEntity, Long> {

}
