package com.formacion.app.Milestone;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.formacion.app.Order.OrderPairRequest;
import com.formacion.app.Roadmap.Roadmap;
import com.formacion.app.Roadmap.RoadmapServices;
import com.formacion.app.Task.Task;


@Service
public class MilestoneServices {
  @Autowired
  private MilestoneRepository milestoneRepository;
  @Autowired
  private RoadmapServices roadmapServices;


  public MilestoneServices() {
  }

  public Optional<Milestone> findMilestone(Integer milestoneId){
      return this.milestoneRepository.findById(milestoneId);
  }

  public Milestone create(Milestone milestone) {
      roadmapServices.create(milestone.getRoadmap());
      return this.milestoneRepository.save(milestone);
  }

  public ResponseEntity<Milestone> modify(Integer id, RequestMilestoneEdition milestone) {
          Optional<Milestone> milerstoneToModify = this.milestoneRepository.findById(id);
          List<Task> taskToAdd = new ArrayList<Task>();

          if (milerstoneToModify.isPresent()) {
          milerstoneToModify.get().setContent(milestone.getContent());
          milerstoneToModify.get().setName(milestone.getName());
          for (Task task : milestone.getTasks()) {
            task.setMilestone(milerstoneToModify.get());
            taskToAdd.add(task);
          }
          milerstoneToModify.get().setOrderValue(milestone.getOrderValue());
          milerstoneToModify.get().setTasks(taskToAdd);
          Optional<Roadmap> existingRoadmap = roadmapServices.findRoadmap(milestone.getRoadmapId());
          if (existingRoadmap.isPresent()) {
            milerstoneToModify.get().setRoadmap(existingRoadmap.get());
          }
          return new ResponseEntity<Milestone>(this.milestoneRepository.save(milerstoneToModify.get()),HttpStatus.OK);
          } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
  }



  public void deleteMilestone(Integer id) {
        this.milestoneRepository.deleteById(id);
  }

  public ResponseEntity<String> sortMilestones(List<OrderPairRequest> orderPairsRequest) {

    for (OrderPairRequest orderPairRequest : orderPairsRequest) {
        Optional<Milestone> roadmap = this.milestoneRepository.findById(orderPairRequest.getId());
        if (roadmap.isPresent()) {
            roadmap.get().setOrderValue(orderPairRequest.getOrderValue());
            this.milestoneRepository.save(roadmap.get());
        } else {
            return new ResponseEntity<String>(String.format("the milestone with the id %s was no found", orderPairRequest.getOrderValue()),HttpStatus.NOT_FOUND);
        }
    }
    return new ResponseEntity<String>("milestones Sorted Succesfully",HttpStatus.OK);
  }



    

}
