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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        // Correct the mock to return Optional<User> rather than calling get() directly.
        when(userRepo.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(User.builder()
                        .userName("Santosh")
                        .password("Santosh")
                        .roles(new ArrayList<>(List.of("USER")))
                        .build()));

        UserDetails usr = userDetailServiceImpl.loadUserByUsername("Santosh");
        assertNotNull(usr);
    }
}
