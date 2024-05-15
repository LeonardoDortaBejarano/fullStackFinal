package com.formacion.app.Task;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.formacion.app.Task.Task;
import com.formacion.app.Task.TaskRepository;
import com.formacion.app.Milestone.Milestone;
import com.formacion.app.Milestone.MilestoneServices;
import com.formacion.app.Milestone.Milestone;
import com.formacion.app.Milestone.MilestoneServices;

@Service
public class TaskService {

 @Autowired
  private TaskRepository taskRepository;
  @Autowired
  private MilestoneServices milestoneServices;


  public TaskService() {
  }

  public Optional<Task> findTask(Integer taskId){
      return this.taskRepository.findById(taskId);
  }

  public Task create(Task task) {
      milestoneServices.create(task.getMilestone());
      return this.taskRepository.save(task);
  }

  public ResponseEntity<Task> modify(Integer id, Task task) {
          Optional<Task> taskToModify = this.taskRepository.findById(id);
          if (taskToModify.isPresent()) {
            taskToModify.get().setComplete(task.isComplete());
            taskToModify.get().setName(task.getName());
            taskToModify.get().setOrderValue(task.getOrderValue());
            taskToModify.get().setLink(task.getLink());
/*             Optional<Milestone> existingMilestone = milestoneServices.findMilestone(task.getMilestone().getId());
            if (existingMilestone.isPresent()) {
                taskToModify.get().setMilestone(existingMilestone.get());
            } */
            this.taskRepository.save(taskToModify.get());
            return new ResponseEntity<Task>(taskToModify.get(), HttpStatus.OK);
          } else {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
          }
  }

  public void deleteTask(Integer id) {
          this.taskRepository.deleteById(id);
  }

  

    
    

}
