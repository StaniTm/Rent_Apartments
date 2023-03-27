package com.example.myproject.domain.helper;


import com.example.myproject.domain.model.dtoS.user.UserRoleModel;

public class LoggedUser {

    private Long id;

    private String username;

    private String password;

    private UserRoleModel role;

    public boolean isEmpty() {
        return this.id == null;
    }

    public boolean isAdmin() {
        return this.role.getRole().toString().equals("ADMIN");
    }

    public LoggedUser() {
    }

    public Long getId() {
        return id;
    }

    public LoggedUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoggedUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserRoleModel getRole() {
        return role;
    }

    public LoggedUser setRole(UserRoleModel role) {
        this.role = role;
        return this;
    }

    public void clearUser() {
        this.id = null;
        this.password = null;
        this.username = null;
    }
}
