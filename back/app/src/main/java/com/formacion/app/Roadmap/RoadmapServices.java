package com.formacion.app.Roadmap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.formacion.app.Milestone.Milestone;
import com.formacion.app.Milestone.MilestoneRepository;
import com.formacion.app.Task.Task;
import com.formacion.app.Task.TaskRepository;
import com.formacion.app.User.User;
import com.formacion.app.User.UserRepository;

@Service
public class RoadmapServices {
    @Autowired
    private RoadmapRepository roadmapRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MilestoneRepository milestoneRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ModelMapper modelMapper;

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

    public ResponseEntity<RoadmapDto> createRoadmapForUser(Integer id, RequestRoadmap requestRoadmap) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            Roadmap roadmap = new Roadmap(requestRoadmap.getName(),requestRoadmap.getDescription(),new Date(), user.get());
            roadmap.setColor(requestRoadmap.getColor());
            RoadmapDto roadmapDto = this.convertToDto(this.roadmapRepository.save(roadmap));
            return new ResponseEntity<RoadmapDto>(roadmapDto,HttpStatus.OK);
        }   else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }  
    }

    public List<Roadmap> getRoadmapsByUserId(Integer id) {
        return this.roadmapRepository.findByUserId(id);
    }

    public ResponseEntity<Milestone> createMilestoneForRoamap(Integer id, Milestone milestone) {
        Optional<Roadmap> roadmap = this.roadmapRepository.findById(id);
        List<Task> createdTask = new ArrayList<>();
        if (roadmap.isPresent()) {
            Milestone newMilestone = new Milestone();
            newMilestone.setName(milestone.getName());
            newMilestone.setContent(milestone.getContent());
            newMilestone.setRoadmap(roadmap.get());
            
            this.milestoneRepository.save(newMilestone);
            if (milestone.getTasks() != null && !milestone.getTasks().isEmpty()) {
                List<Task> tasks = milestone.getTasks();
                for (Task task : tasks) {
                    Task taskToSave = new Task();
                    taskToSave.setName(task.getName());
                    taskToSave.setMilestone(newMilestone);
                    createdTask.add(taskRepository.save(taskToSave));
                }
                newMilestone.setTasks(createdTask);
            }
            
            return new ResponseEntity<Milestone>(newMilestone,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public List<Roadmap> searchRoadmapByQuery(String query) {
        return this.roadmapRepository.findByNameContaining(query);
    }

    public Integer getTaskQuantity(Roadmap roadmap) {
        if (roadmap.getMilestones() == null) {
            return 0;
        }
        
        Integer taskQuantity = 0;
        for (Milestone milestone : roadmap.getMilestones()) {
            taskQuantity += milestone.getTasks().size();
        }
        return taskQuantity;
    }

    public Integer getDoneTaskQuantity(Roadmap roadmap) {
        if (roadmap.getMilestones() == null) {
            return 0;
        }

        Integer doneTaskQuantity = 0;
        for (Milestone milestone : roadmap.getMilestones()) {
            for (Task task : milestone.getTasks())
            if (task.isComplete()) {
                ++doneTaskQuantity;
            }
        }
        return doneTaskQuantity;
    }

    public Float getDonePercentage(Roadmap roadmap) {
        if (this.getTaskQuantity(roadmap)!=0) {
            System.out.println(this.getDoneTaskQuantity(roadmap) / this.getTaskQuantity(roadmap) * 100);
            return ((float)this.getDoneTaskQuantity(roadmap) / (float)this.getTaskQuantity(roadmap)) * 100;
            
        }
        return (float)0;
    }

    public RoadmapDto convertToDto(Roadmap roadmap) {
        RoadmapDto roadmapDto = modelMapper.map(roadmap, RoadmapDto.class);
        roadmapDto.setTotalTask(this.getTaskQuantity(roadmap)); 
        roadmapDto.setTotalDoneTask(this.getDoneTaskQuantity(roadmap));
        roadmapDto.setDonePercentage(this.getDonePercentage(roadmap));
        return roadmapDto;
    }

    public ResponseEntity<RoadmapDto> updateRoadmapProperties(Integer id, RequestRoadmap roadmapProperties) {
        Optional<Roadmap> roadmap = this.roadmapRepository.findById(id);
        if (roadmap.isPresent()) {
            roadmap.get().setName(roadmapProperties.getName());
            roadmap.get().setDescription(roadmapProperties.getDescription());
            roadmap.get().setColor(roadmapProperties.getColor());
            RoadmapDto roadmapDto = this.convertToDto(this.roadmapRepository.save(roadmap.get()));
            return new ResponseEntity<RoadmapDto>(roadmapDto,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }  
    }

    

    







}
