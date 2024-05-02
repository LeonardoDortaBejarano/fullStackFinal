package com.formacion.app.Milestone;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.app.Milestone.Milestone;

@RestController
@RequestMapping("milestone")
public class MilestoneController {
  @Autowired
  private MilestoneServices milestoneServices;

  public MilestoneController() {
  }

 @GetMapping({"{id}"})
    public ResponseEntity<Milestone> getMilestone(@PathVariable("id") Integer id) {
        Optional<Milestone> milestone = this.milestoneServices.findMilestone(id);
        if (milestone.isPresent()) {
            return new ResponseEntity<Milestone>(milestone.get(),HttpStatus.OK);
        }   else {
            return new ResponseEntity<Milestone>(HttpStatus.NOT_FOUND);
        }  
    }

    @PostMapping()
    public ResponseEntity<Milestone> createMilestone(@RequestBody Milestone milestone) {
        Milestone milestoneRespond = this.milestoneServices.create(milestone);
        return new ResponseEntity<Milestone>(milestoneRespond,HttpStatus.OK); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<Milestone> modifyMilestone(@PathVariable("id") Integer id, @RequestBody Milestone milestone) {
        HttpStatus httpStatus = this.milestoneServices.modify(id, milestone);
        return new ResponseEntity<Milestone>(httpStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Milestone> deleteMilestone(@PathVariable("id") Integer id) {
        this.milestoneServices.deleteMilestone(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





    

}
