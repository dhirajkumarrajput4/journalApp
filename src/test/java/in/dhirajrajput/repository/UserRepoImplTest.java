package in.dhirajrajput.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepoImplTest {

    @Autowired
    public UserRepoImpl userRepoImpl;


    @Test
    public void testGetUser(){
        assertNotNull(userRepoImpl.getUser());
    }
    
}
