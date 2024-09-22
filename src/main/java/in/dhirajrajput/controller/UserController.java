package in.dhirajrajput.controller;

import in.dhirajrajput.entity.User;
import in.dhirajrajput.response_request.UserDto;
import in.dhirajrajput.response_request.WeatherResponse;
import in.dhirajrajput.service.UserService;
import in.dhirajrajput.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

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
    public ResponseEntity<User> updateJournalEtity(@RequestBody UserDto user) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User existingUser = this.userService.findByUserName(userName).orElse(null);
        if (existingUser != null) {
            userService.updateUserDetails(user, existingUser);
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/fetch-weather")
    public ResponseEntity<?> getWeatherData() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        WeatherResponse weather = weatherService.getWeatherResponse("New Delhi");
        String greeting = "";
        if (weather != null) {
            greeting = ", Weather feel like " + weather.getCurrent().getFeelsLike() + " degree Celsius";
        }
        return new ResponseEntity<>("Hi " + userName + greeting, HttpStatus.OK);
    }
}
