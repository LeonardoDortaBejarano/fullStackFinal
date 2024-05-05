package com.formacion.app.Roadmap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.app.Milestone.Milestone;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping(value = "roadmap")
@CrossOrigin(origins = "http://localhost:4200")
public class RoadmapController {

    @Autowired
    private RoadmapServices roadMapService;

    @GetMapping({"{id}"})
    public ResponseEntity<Roadmap> getRoadmap(@PathVariable("id") Integer id) {
        Optional<Roadmap> roadMap = this.roadMapService.findRoadmap(id);
        if (roadMap.isPresent()) {
            return new ResponseEntity<Roadmap>(roadMap.get(),HttpStatus.OK);
        }   else {
            return new ResponseEntity<Roadmap>(HttpStatus.NOT_FOUND);
        }  
    }

    @PostMapping()
    public ResponseEntity<Roadmap> createRoadmap(@RequestBody Roadmap roadmap) {
        Roadmap roadmapRespond = this.roadMapService.create(roadmap);
        return new ResponseEntity<Roadmap>(roadmapRespond,HttpStatus.OK); 
    }

    @PostMapping("/{id}/milestone")
    public ResponseEntity<Milestone> createMilestoneForRoamap(@PathVariable("id") Integer id, @RequestBody Milestone milestone) {
         return this.roadMapService.createMilestoneForRoamap(id,milestone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Roadmap> modifyRoadmap(@PathVariable("id") Integer id, @RequestBody Roadmap roadmap) {
        HttpStatus httpStatus = this.roadMapService.modify(id, roadmap);
        return new ResponseEntity<Roadmap>(httpStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Roadmap> deleteRoadmap(@PathVariable("id") Integer id) {
        this.roadMapService.deleteRoadmap(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
