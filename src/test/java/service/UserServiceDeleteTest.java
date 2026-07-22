package service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceDeleteTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @Test
    void deleteUser_shouldReturnTrue_whenUserExistsAndDeleted(){
        Long userId = 3L;
        User user = new User();
        user.setName("Eva");
        user.setEmail("eva@gmail.com");
        user.setAge(String.valueOf(22));
        user.setId(userId);

        when(repository.findUserById(userId)).thenReturn(user);

        boolean result = userService.deleteUserById(userId);

        assertTrue(result,"Return true if deleted success");

        verify(repository).findUserById(userId);
        verify(repository).deleteUserById(userId);
        verifyNoMoreInteractions(repository);
    }
}
