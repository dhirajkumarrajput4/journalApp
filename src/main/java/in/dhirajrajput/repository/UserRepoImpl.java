package in.dhirajrajput.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import in.dhirajrajput.entity.User;

public class UserRepoImpl {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUser(){
        Query query=new Query();
        query.addCriteria(Criteria.where("userName").is("mohit"));
        return mongoTemplate.find(query, User.class);
    }

    public List<User> getUserForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").exists(true));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        return mongoTemplate.find(query, User.class);
    }
}
