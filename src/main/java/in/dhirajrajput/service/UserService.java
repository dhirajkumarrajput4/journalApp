package in.dhirajrajput.service;

import in.dhirajrajput.entity.User;
import in.dhirajrajput.repository.UserRepo;
import in.dhirajrajput.response_request.UserDto;
import lombok.extern.slf4j.Slf4j;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(UserDto userDto) {
        try {
            User user = new User();
            user.setUserName(userDto.getUserName());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            return this.userRepo.save(user);
        } catch (Exception exception) {
            log.error("Getting error while saving user for: {}", userDto.toString());
            throw new RuntimeException("Getting error while saving the user" + exception.getMessage());
        }
    }

    public void updateUser(User user) {
        this.userRepo.save(user);
    }

    public void updateUserDetails(UserDto user, User existingUser) {
        try {
            if (existingUser != null) {
                existingUser.setUserName(user.getUserName());
                existingUser.setPassword(user.getPassword());
                updateUser(existingUser);
            }
        else{
                log.error("User Not found for update.");
                throw new IllegalStateException("User not found");
            }
        } catch (Exception exception) { 
            log.error("This user not able to update." + user);
            throw new IllegalStateException("Unable to update user.");
        }

    }

    public List<User> findAllUsers() {
        return this.userRepo.findAll();
    }

    public Optional<User> findUserByid(ObjectId id) {
        return this.userRepo.findById(id);
    }

    public void deleteUser(User user) {
        this.userRepo.delete(user);
    }

    public void deleteUserById(ObjectId id) {
        this.userRepo.deleteById(id);
    }

    public Optional<User> findByUserName(String userName) {
        return this.userRepo.findByUserName(userName);
    }
}
