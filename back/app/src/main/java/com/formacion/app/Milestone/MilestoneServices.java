package com.formacion.app.Milestone;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.formacion.app.Roadmap.Roadmap;
import com.formacion.app.Roadmap.RoadmapServices;


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

  public HttpStatus modify(Integer id, Milestone milestone) {
          Optional<Milestone> milerstoneToModify = this.milestoneRepository.findById(id);
          if (milerstoneToModify.isPresent()) {
          milerstoneToModify.get().setContent(milestone.getContent());
          milerstoneToModify.get().setName(milestone.getName());
          milerstoneToModify.get().setOrderValue(milestone.getOrderValue());
          Optional<Roadmap> existingRoadmap = roadmapServices.findRoadmap(milestone.getRoadmap().getId());
          if (existingRoadmap.isPresent()) {
            milerstoneToModify.get().setRoadmap(existingRoadmap.get());
          }
          this.milestoneRepository.save(milerstoneToModify.get());
          return HttpStatus.OK;
          } else {
          return HttpStatus.NOT_FOUND;
          }
  }

  public void deleteMilestone(Integer id) {
          this.milestoneRepository.deleteById(id);
  }

    

}
