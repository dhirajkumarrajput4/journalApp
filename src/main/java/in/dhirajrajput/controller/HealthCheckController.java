package in.dhirajrajput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dhirajrajput.entity.User;
import in.dhirajrajput.service.UserService;

@RestController
@RequestMapping("/public")
public class HealthCheckController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
     public  ResponseEntity<?>  healthCheck(){
         return new ResponseEntity<>("working fine",HttpStatus.OK);
     }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        this.userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
