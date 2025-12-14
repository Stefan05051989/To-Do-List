// controllers/UserController.java
package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.controllers;

import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user.UserChangePasswordDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user.UserCreateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user.UserSummaryDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.dto.user.UserUpdateDTO;
import com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserSummaryDTO> create(@RequestBody UserCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserSummaryDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSummaryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserSummaryDTO> update(@PathVariable Long id, @RequestBody UserUpdateDTO dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change-password")
    public ResponseEntity<UserSummaryDTO> changePassword(@RequestBody UserChangePasswordDTO dto) {
        return ResponseEntity.ok(userService.changePassword(dto));
    }
}