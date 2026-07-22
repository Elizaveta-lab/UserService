package service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceFindTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @Test
    void findUserById_shouldReturnUser_whenExists() {
        Long userId = 1L;
        User user = new User();
        user.setName("Eva");
        user.setEmail("eva@gmail.com");
        user.setAge(String.valueOf(22));

        when(repository.findUserById(userId)).thenReturn(user);

        User result = userService.findUserById(userId);

        assertNotNull(result);
        assertSame(user, result);

        verify(repository).findUserById(userId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findUserById_shouldThrow_whenUserNotFound(){
        Long userId = 999L;

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> userService.findUserById(userId)
        );
        assertTrue(ex.getMessage().contains("not found"));

        verify(repository).findUserById(userId);
        verifyNoMoreInteractions(repository);
    }
}
