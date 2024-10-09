package in.dhirajrajput.controller;

import in.dhirajrajput.response_request.UserDto;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import in.dhirajrajput.entity.User;

import java.util.*;

import in.dhirajrajput.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/admin")
@Tag(name = "Admin APIs")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
        this.userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = this.userService.findAllUsers();
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") ObjectId objectId) {
        Optional<User> userByid = this.userService.findUserByid(objectId);
        return userByid.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/byId/{id}")
    public ResponseEntity<?> deleteEntityById(@PathVariable("id") ObjectId id) {
        this.userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UserDto user) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User existingUser = this.userService.findByUserName(userName).orElse(null);
        if (existingUser != null) {
            userService.updateUserDetails(user, existingUser);
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
