package com.formacion.app.User;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;


@SpringBootTest
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository underTest;

    UserRepositoryTest(){}

    @Test
    void ItShouldFindUserByUsername() {
        //given
        User user = new User();
        String username = "another";
        user.setUsername(username);

        underTest.save(user);
        //when
        Optional<User> result = this.underTest.findByUsername(username);
        //then
        assertThat(result.get()).isEqualTo(user);
    }
}
