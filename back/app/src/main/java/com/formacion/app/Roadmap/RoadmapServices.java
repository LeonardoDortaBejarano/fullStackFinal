package com.formacion.app.Roadmap;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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



}
