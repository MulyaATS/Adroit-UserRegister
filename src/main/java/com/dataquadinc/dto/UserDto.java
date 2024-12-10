package com.dataquadinc.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    @Id
    @NotEmpty(message = "User ID cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{8}$", message = "User ID must be alphanumeric and exactly 8 characters long")
    private String userId; // User ID

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName; // Username

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    private String password; // Password

    @NotEmpty(message = "Confirm Password cannot be empty")
    @Size(min = 8, max = 50, message = "Confirm Password must be between 8 and 50 characters")
    private String confirmPassword; // Confirm Password

    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email cannot be empty")
    private String email; // Email

    @Email(message = "Invalid personal email format")
    @NotEmpty(message = "Personal Email cannot be empty")
    private String personalemail; // Personal Email

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be a 10-digit number")
    private String phoneNumber; // Phone Number

    @NotEmpty(message = "Designation cannot be empty")
    @Size(min = 3, max = 100, message = "Designation must be between 3 and 100 characters")
    private String designation; // Designation

    // New fields in UserDto

    @NotEmpty(message = "Gender cannot be empty")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
    private String gender; // Gender field with values: Male, Female, or Other

    @Past(message = "Date of birth must be a past date")
    private LocalDate dateOfBirth; // Date of Birth (should be in the past)

    @NotEmpty(message = "Date of joining cannot be empty")
    private LocalDate dateOfJoining; // Date of Joining (cannot be empty)

    private String Roles;

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String roles) {
        Roles = roles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public @NotEmpty String getUserName() {
        return userName;
    }

    public void setUserName(@NotEmpty String userName) {
        this.userName = userName;
    }

    public @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public @NotEmpty String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NotEmpty String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public @Email @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotEmpty String email) {
        this.email = email;
    }

    public @Email String getPersonalemail() {
        return personalemail;
    }

    public void setPersonalemail(@Email String personalemail) {
        this.personalemail = personalemail;
    }

    public @NotEmpty @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number") String getPhoneNumber() {
        return phoneNumber;
    }

    public @NotEmpty(message = "Gender cannot be empty") @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other") String getGender() {
        return gender;
    }

    public void setGender(@NotEmpty(message = "Gender cannot be empty") @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other") String gender) {
        this.gender = gender;
    }

    public @Past(message = "Date of birth must be a past date") LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@Past(message = "Date of birth must be a past date") LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotEmpty(message = "Date of joining cannot be empty") LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(@NotEmpty(message = "Date of joining cannot be empty") LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public void setPhoneNumber(@NotEmpty @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotEmpty String getDesignation() {
        return designation;
    }

    public void setDesignation(@NotEmpty String designation) {
        this.designation = designation;
    }



    public Object getUsername() {
        return this.userName;
    }

    public void setMessage(String s) {
    }


// Getters and setters

}
