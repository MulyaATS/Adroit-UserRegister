package com.dataquadinc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
public class UserDetails {

        @Id
        @Column(unique = true, nullable = false)
        @NotEmpty(message = "User ID cannot be empty")
        @Pattern(regexp = "^[a-zA-Z0-9]{8}", message = "User ID must be alphanumeric (letters and numbers only)")
        private String userId; // This is set manually from the frontend

        @NotEmpty(message = "Username cannot be empty")
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        private String userName;

        @NotEmpty(message = "Password cannot be empty")
        @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
        private String password;

        @NotEmpty(message = "Confirm Password cannot be empty")
        @Size(min = 8, max = 50, message = "Confirm Password must be between 8 and 50 characters")
        private String confirmPassword;

        @Email(message = "Invalid email format")
        @Column(unique = true, nullable = false)
        private String email;

        @Email(message = "Invalid personal email format")
        @Column(unique = true, nullable = false)
        private String personalemail;

        @NotEmpty(message = "Phone number cannot be empty")
        @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be a 10-digit number")
        private String phoneNumber;

        @NotEmpty(message = "Designation cannot be empty")
        @Size(min = 3, max = 100, message = "Designation must be between 3 and 100 characters")
        private String designation;

        // New fields with validation
        @NotEmpty(message = "Gender cannot be empty")
        @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
        private String gender; // Gender field with values: Male, Female, or Other

        @Past(message = "Date of birth must be a past date")
        private LocalDate dateOfBirth; // Date of Birth (should be in the past)


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

        @NotEmpty(message = "Date of joining cannot be empty")
        private LocalDate dateOfJoining; // Date of Joining (cannot be empty)


        @CreationTimestamp
        @Column(name = "created_at")
        private LocalDateTime createdAt= LocalDateTime.now();;

        @UpdateTimestamp
        @Column(name = "updated_at")
        private LocalDateTime updatedAt=LocalDateTime.now();;



        @Column(name = "last_Login_Time")
        private LocalDateTime lastLoginTime;

        public String getRoles() {
                return roles;
        }

        public void setRoles(String roles) {
                this.roles = roles;
        }

        private String roles ;

        public String getUserId() {
                return userId;
        }

        public void setUserId(String userId) {
                this.userId = userId;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public @Email String getEmail() {
                return email;
        }

        public void setEmail(@Email String email) {
                this.email = email;
        }
        public String getPassword() {
                return password;
        }

             public void setPassword(String password) {
                this.password = password;
        }

        public String getConfirmPassword() {
                return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
                this.confirmPassword = confirmPassword;
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

        public void setPhoneNumber(@NotEmpty @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number") String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }

        public @NotEmpty String getDesignation() {
                return designation;
        }

        public void setDesignation(@NotEmpty String designation) {
                this.designation = designation;
        }

        public LocalDateTime getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
                this.createdAt = createdAt;
        }

        public LocalDateTime getUpdatedAt() {
                return updatedAt;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
                this.updatedAt = updatedAt;
        }

        public LocalDateTime getLastLoginTime() {
                return lastLoginTime;
        }

        public void setLastLoginTime(LocalDateTime lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
        }


        public Object getLoginTimestamp() {
            return this.lastLoginTime;
        }

        public void setLoginTimestamp(LocalDateTime now) {
        }
}
