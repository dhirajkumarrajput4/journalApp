package in.dhirajrajput.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import in.dhirajrajput.entity.User;
import in.dhirajrajput.repository.UserRepo;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Test
    public void testFindByUserName() {
        assertNotNull(userRepo.findByUserName("Santosh"));
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvidor.class)
    public void testSaveUsers(User user) {
        assertNotNull(userService.saveUser(user));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,11,13",
            "9,2,11",
            "1,2,3"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }

}
