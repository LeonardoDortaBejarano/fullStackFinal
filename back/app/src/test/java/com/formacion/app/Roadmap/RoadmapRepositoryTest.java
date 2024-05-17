package com.formacion.app.Roadmap;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.formacion.app.User.User;
import com.formacion.app.User.UserRepository;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
//@DataJpa
public class RoadmapRepositoryTest {
    
    @Autowired
    RoadmapRepository undertest;
    @Autowired
    UserRepository userRepository;

/*     @BeforeEach
    void setUpDataToTest(){
        Roadmap roadmapOne = new Roadmap();
        roadmapOne.setName("roadmapOne");

        Roadmap roadmapTwo = new Roadmap();
        roadmapTwo.setName("roadmapTwo");

        this.undertest.save(roadmapOne);
        this.undertest.save(roadmapTwo);
    } */




    @Test
    void itShouldFindRoadmapsByQuery() {
        //given
        Roadmap roadmapOne = new Roadmap();
        roadmapOne.setName("roadmapOne");

        Roadmap roadmapTwo = new Roadmap();
        roadmapTwo.setName("roadmapTwo");

        this.undertest.save(roadmapOne);
        this.undertest.save(roadmapTwo);
        List<Roadmap> roadmapList = new ArrayList<>();
        roadmapList.add(roadmapOne);
        roadmapList.add(roadmapTwo);
    
        
        //when
        String query = "roadmap"; 
        List<Roadmap> roadmapsResult = undertest.findByNameContaining(query);
        //then
        assertThat(roadmapsResult).isEqualTo(roadmapList);
    }

    @Test
    void findByUserIdOrderByOrderValue() {
        //given
        User user = new User();
        String username = "another";
        user.setUsername(username);
        this.userRepository.save(user);
        Optional<User> userWithId = this.userRepository.findByUsername(username);

        Roadmap roadmapOne = new Roadmap();
        roadmapOne.setName("roadmapOne");
        roadmapOne.setOrderValue(1);
        roadmapOne.setUser(userWithId.get());
        this.undertest.save(roadmapOne);

        Roadmap roadmapTwo = new Roadmap();
        roadmapTwo.setName("roadmapTwo");
        roadmapTwo.setOrderValue(0);
        roadmapTwo.setUser(userWithId.get());
        this.undertest.save(roadmapTwo);

        List<Roadmap> roadmapList = new ArrayList<>();
        roadmapList.add(roadmapTwo);
        roadmapList.add(roadmapOne);
        // when
        List<Roadmap> roadmapsResutl = this.undertest.findByUserIdOrderByOrderValue(user.getId());
        // then
        assertThat(roadmapsResutl).isEqualTo(roadmapList);
    }

}
