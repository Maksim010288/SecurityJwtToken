package com.baziuk.SpringSecuredDemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users_role")
@NoArgsConstructor
@AllArgsConstructor
public class UsersRolesEntity {
    @Id

    @Column(name = "users_id")
    public Integer userId;

    @Column(name = "roles_id")
    public Integer rolesId;
}
