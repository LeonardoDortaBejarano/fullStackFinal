package com.formacion.app.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.formacion.app.Roadmap.RequestRoadmap;
import com.formacion.app.Roadmap.Roadmap;
import com.formacion.app.Roadmap.RoadmapDto;
import com.formacion.app.Roadmap.RoadmapRepository;
import com.formacion.app.Roadmap.RoadmapServices;
import com.formacion.app.User.UserService;




@RestController
@RequestMapping(value = "user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoadmapServices roadmapServices;



    @GetMapping({"{id}"})
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        Optional<User> user = this.userService.findUser(id);
        if (user.isPresent()) {
            return new ResponseEntity<User>(user.get(),HttpStatus.OK);
        }   else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }  
    }

    @GetMapping("{id}/roadmap")
    public ResponseEntity<List<RoadmapDto>> getUserRoadmaps(@PathVariable("id") Integer id) {
        
        List<Roadmap> roadmaps = this.roadmapServices.getRoadmapsByUserId(id);
        List<RoadmapDto> roadmapsDto =  roadmaps.stream().map(this.roadmapServices::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<List<RoadmapDto>>(roadmapsDto,HttpStatus.OK);
    }  
    

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userRespond = this.userService.create(user);
        return new ResponseEntity<User>(userRespond,HttpStatus.OK); 
    }

    @PostMapping("{id}/roadmap")
    public ResponseEntity<Roadmap> createRoadmapForUser(@PathVariable("id") Integer id, @RequestBody RequestRoadmap requestRoadmap ) {
        return this.roadmapServices.createRoadmapForUser(id,requestRoadmap);
    }




    @PutMapping("/{id}")
    public ResponseEntity<User> modifyUser(@PathVariable("id") Integer id, @RequestBody User user) {
        HttpStatus httpStatus = this.userService.modify(id, user);
        return new ResponseEntity<User>(httpStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        this.userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
    
    

}
