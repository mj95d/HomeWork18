package com.example.home18.Service;

import com.example.home18.ApiResponse.ApiResponse;
import com.example.home18.Model.User;
import com.example.home18.Repository.UserRepository;
import jakarta.validation.Valid;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ApiResponse addUser(@Valid User user) {
        try {
            userRepository.save(user);
            return new ApiResponse(true, "User added successfully", null);
        } catch (Exception e) {
            return new ApiResponse(false, "Error adding user", e.getMessage());
        }
    }

    public ApiResponse updateUser(@Valid User user) {
        try {
            Optional<User> optionalUser = userRepository.findById(user.getId());
            if (optionalUser.isPresent()) {
                userRepository.save(user);
                return new ApiResponse(true, "User updated successfully", null);
            } else {
                return new ApiResponse(false, "User not found", null);
            }
        } catch (Exception e) {
            return new ApiResponse(false, "Error updating user", e.getMessage());
        }
    }

    public ApiResponse deleteUser(Long id) {
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                userRepository.deleteById(id);
                return new ApiResponse(true, "User deletedsuccessfully", null);
            } else {
                return new ApiResponse(false, "User not found", null);
            }
        } catch (Exception e) {
            return new ApiResponse(false, "Error deleting user", e.getMessage());
        }
    }

    public ApiResponse checkUsernameAndPassword(String username, String password) {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                return new ApiResponse(false, "Invalid username", null);
            }
            if (user.getPassword().equals(password)) {
                return new ApiResponse(true, "Username and password are correct", null);
            } else {
                return new ApiResponse(false, "Invalid password", null);
            }
        } catch (Exception e) {
            return new ApiResponse(false, "Error checking username and password", e.getMessage());
        }
    }

    public ApiResponse getUserByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                return new ApiResponse(false, "User not found", null);
            }
            return new ApiResponse(true, "User found", user);
        } catch (Exception e) {
            return new ApiResponse(false, "Error getting user by email", e.getMessage());
        }
    }

    public ApiResponse getUsersByRole(String role) {
        try {
            List<User> users = userRepository.findByRole(role);
            if (users.isEmpty()) {
                return new ApiResponse(false, "No users found with this role", null);
            }
            Continuation:

            return new ApiResponse(true, "Users found with this role", users);
        } catch (Exception e) {
            return new ApiResponse(false, "Error getting users by role", e.getMessage());
        }
    }

    public ApiResponse getUsersByAgeOrAbove(Integer age) {
        try {
            List<User> users = userRepository.findByAgeGreaterThanEqualOrderByAgeAsc(age);
            if (users.isEmpty()) {
                return new ApiResponse(false, "No users found with this age or above", null);
            }
            return new ApiResponse(true, "Users found with this age or above", users);
        } catch (Exception e) {
            return new ApiResponse(false, "Error getting users by age", e.getMessage());
        }
    }
}