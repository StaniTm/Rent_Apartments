package com.example.myproject.domain.model.dtoS.user;


import com.example.myproject.domain.enums.Role;

public class UserRoleModel {

    private Long id;

    private Role role;

    public UserRoleModel() {
    }

    public UserRoleModel(Long id, Role role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
