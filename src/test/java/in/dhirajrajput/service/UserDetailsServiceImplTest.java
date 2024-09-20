package in.dhirajrajput.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import in.dhirajrajput.entity.User;
import in.dhirajrajput.repository.UserRepo;

@SpringBootTest
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailServiceImpl userDetailServiceImpl;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserByUserNameTest() {
        when(userRepo.findByUserName(ArgumentMatchers.anyString()).get())
                .thenReturn(User.builder().userName("ram")
                .password("ram")
                .build());

        UserDetails usr = userDetailServiceImpl.loadUserByUsername("ram");
        assertNotNull(usr);

    }
}
