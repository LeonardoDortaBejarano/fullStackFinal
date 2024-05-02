package com.formacion.app.Roadmap;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.formacion.app.User.User;
import com.formacion.app.User.UserRepository;

@Service
public class RoadmapServices {
    @Autowired
    private RoadmapRepository roadmapRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<Roadmap> findRoadmap(Integer roadmapId){
        return this.roadmapRepository.findById(roadmapId);
    }

    public Roadmap create(Roadmap roadmap) {
        userRepository.save(roadmap.getUser());
        return this.roadmapRepository.save(roadmap);
    }

    public HttpStatus modify(Integer id, Roadmap roadmap) {
         Optional<Roadmap> roadmapToModify = this.roadmapRepository.findById(id);
         if (roadmapToModify.isPresent()) {
            roadmapToModify.get().setCreated_date(roadmap.getCreated_date());
            roadmapToModify.get().setMilestones(roadmap.getMilestones());
            roadmapToModify.get().setName(roadmap.getName());
            Optional<User> existingUser = userRepository.findById(roadmap.getUser().getId());
            if (existingUser.isPresent()) {
                roadmapToModify.get().setUser(existingUser.get());
            }
            this.roadmapRepository.save(roadmapToModify.get());
            return HttpStatus.OK;
         } else {
            return HttpStatus.NOT_FOUND;
         }
    }

    public void deleteRoadmap(Integer id) {
         this.roadmapRepository.deleteById(id);
    }

    public ResponseEntity<Roadmap> createRoadmapForUser(Integer id, RequestRoadmap requestRoadmap) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            Roadmap roadmap = new Roadmap(requestRoadmap.getName(),requestRoadmap.getDescription(),new Date(), user.get());
            this.roadmapRepository.save(roadmap);
            return new ResponseEntity<Roadmap>(roadmap,HttpStatus.OK);
        }   else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }  
    }

    public List<Roadmap> getRoadmapsByUserId(Integer id) {
        return this.roadmapRepository.findByUserId(id);
    }





}
