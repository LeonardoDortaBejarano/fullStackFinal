package com.formacion.app.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.formacion.app.Roadmap.Roadmap;

@Service
public class UserService {
    
    @Autowired UserRepository userRepository;

    public UserService(){}

    public Optional<User> findUser(Integer Userid){
        return this.userRepository.findById(Userid);
    }

    public User create(User user) {
        return this.userRepository.save(user);
    }

    public HttpStatus modify(Integer id, User user) {
         Optional<User> userToModify = this.userRepository.findById(id);
         if (userToModify.isPresent()) {
            userToModify.get().setCreationDate(user.getCreationDate());
            userToModify.get().setEmail(user.getEmail());
            userToModify.get().setUsername(user.getUsername());
            this.userRepository.save(userToModify.get());
            return HttpStatus.OK;
         } else {
            return HttpStatus.NOT_FOUND;
         }
    }

    public void  deleteUser(Integer id) {
         this.userRepository.deleteById(id);
    }





}
