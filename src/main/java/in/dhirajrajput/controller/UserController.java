package in.dhirajrajput.controller;

import in.dhirajrajput.entity.User;
import in.dhirajrajput.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        this.userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = this.userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
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

    @PutMapping("/{userName}")
    public ResponseEntity<User> updateJournalEtity(@PathVariable("userName") String userName,
            @RequestBody User user) {
        User existingUser = this.userService.findByUserName(userName).orElse(null);
        if (existingUser != null) {
            existingUser.setUserName(user.getUserName());
            existingUser.setPassword(user.getPassword());
            userService.saveUser(existingUser);
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
