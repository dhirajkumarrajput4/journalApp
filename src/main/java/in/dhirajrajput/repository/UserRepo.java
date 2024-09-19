package in.dhirajrajput.repository;

import in.dhirajrajput.entity.User;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {

    Optional<User> findByUserName(String userName);
}
