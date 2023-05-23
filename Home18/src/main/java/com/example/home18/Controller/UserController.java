package com.example.home18.Controller;

import com.example.home18.ApiResponse.ApiResponse;
import com.example.home18.Service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse(true, "Users found", userService.getAllUsers()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody User user) {
        User savedUser = userService.addUser(user);
        ApiResponse response = new ApiResponse(true, "User added successfully", savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        ApiResponse response = new ApiResponse(true, "User updated successfully", updatedUser);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> checkUsernameAndPassword(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(userService.checkUsernameAndPassword(username, password));
    }

    @GetMapping("/email")
    public ResponseEntity<ApiResponse> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/role")
    public ResponseEntity<ApiResponse> getUsersByRole(@RequestParam String role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    @GetMapping("/age")
    public ResponseEntity<ApiResponse> getUsersByAgeOrAbove(@RequestParam Integer age) {
        return ResponseEntity.ok(userService.getUsersByAgeOrAbove(age));
    }
}