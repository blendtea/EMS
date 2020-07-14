package core.service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.MockitoAnnotations.*;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import core.model.User;
import core.repository.RoleRepository;
import core.repository.UserRepository;

public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    @SuppressWarnings("unused")
	private UserService userServiceUnderTest;
    private User user;

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository,
                                               mockRoleRepository,
                                               mockBCryptPasswordEncoder);
        user = User.builder()
                .id(1)
                .name("Gustavo")
                .lastName("Ponce")
//              .email("test@test.com")
                .build();

        Mockito.when(mockUserRepository.save(any()))
                .thenReturn(user);
//      Mockito.when(mockUserRepository.findByEmail(anyString()))
//              .thenReturn(user);
        }

//    @Test
//    public void testFindUserByEmail() {
//        // Setup
//        final String email = "test@test.com";
//
//        // Run the test
//        final User result = userServiceUnderTest.findUserByEmail(email);
//
//        // Verify the results
//        assertEquals(email, result.getEmail());
//    }
//
//    @Test
//    public void testSaveUser() {
//        // Setup
//        final String email = "test@test.com";
//
//        // Run the test
//        User result = userServiceUnderTest.saveUser(User.builder().build());
//
//        // Verify the results
//        assertEquals(email, result.getEmail());
//    }
}
