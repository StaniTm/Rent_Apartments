package com.example.myproject.domain.model.banding.user;


import com.example.myproject.utils.validations.checkUserExists.ValidateLoginUser;

@ValidateLoginUser
public class UserLoginFormModel {
    private String username;

    private String password;

    public UserLoginFormModel() {
    }

    public UserLoginFormModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
