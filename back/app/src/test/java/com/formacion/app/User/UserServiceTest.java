package com.formacion.app.User;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Autowired
    UserService undertest;
    
    @Test
    void testCreate() {

    }

    @Test
    void testDeleteUser() {

    }

    @Test
    void itShouldFindUserByUserId() {

    }

/*     @DisplayName("Dado un id usuario, deberia de buscar ese usuario y modificarlo ")
    @Test
    void ItShouldModifyAUser() {
        User userParameter = new User();
        userParameter.setCreationDate(new Date());
        userParameter.setEmail("email@email.com");
        userParameter.setUsername("example");
        

        User userToFind = new User();
        userParameter.setCreationDate(new Date());
        userParameter.setEmail("pepe@email.com");
        userParameter.setUsername("example");
        userParameter.setCreationDate(new Date());
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        


    } */
}
