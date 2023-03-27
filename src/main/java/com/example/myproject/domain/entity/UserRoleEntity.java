package com.example.myproject.domain.entity;


import com.example.myproject.domain.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserRoleEntity() {
    }

    public Role getRole() {
        return role;
    }

    public UserRoleEntity setRole(Role role) {
        this.role = role;
        return this;
    }
}
