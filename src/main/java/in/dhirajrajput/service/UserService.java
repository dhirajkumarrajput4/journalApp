package in.dhirajrajput.service;

import in.dhirajrajput.entity.User;
import in.dhirajrajput.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        return this.userRepo.save(user);
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

    public Optional<User> findByUserName(String userName){
        return this.userRepo.findByUserName(userName);
    }
}
