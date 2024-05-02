package com.formacion.app.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.formacion.app.User.UserService;




@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping({"{id}"})
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        Optional<User> user = this.userService.findUser(id);
        if (user.isPresent()) {
            return new ResponseEntity<User>(user.get(),HttpStatus.OK);
        }   else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }  
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userRespond = this.userService.create(user);
        return new ResponseEntity<User>(userRespond,HttpStatus.OK); 
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
