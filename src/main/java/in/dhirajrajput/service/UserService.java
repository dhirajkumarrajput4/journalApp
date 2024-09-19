package in.dhirajrajput.service;

import in.dhirajrajput.entity.User;
import in.dhirajrajput.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User saveUser(User user) {
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
