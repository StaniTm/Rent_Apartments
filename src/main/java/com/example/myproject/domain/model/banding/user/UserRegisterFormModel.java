package com.example.myproject.domain.model.banding.user;

import com.example.myproject.utils.validations.matchingPasswords.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@PasswordMatch(password = "password", confirmPassword = "confirmPassword")
public class UserRegisterFormModel {

    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @NotNull
    @Size(min = 4, max = 20)
    private String password;

    @NotNull
    @Size(min = 4, max = 20)
    private String confirmPassword;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 10, max = 14)
    private String phoneNumber;

    private String role;

    private String firstName;

    private String lastName;

    public UserRegisterFormModel() {
    }

    public UserRegisterFormModel(String username, String password, String confirmPassword,
                                 String email, String phoneNumber, String role, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterFormModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterFormModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterFormModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterFormModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegisterFormModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserRegisterFormModel setRole(String role) {
        this.role = role;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterFormModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterFormModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
