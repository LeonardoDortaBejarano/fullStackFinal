package com.formacion.app.Task;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
  @Autowired
  private TaskService taskServices;

  public TaskController() {
  }

 @GetMapping({"{id}"})
    public ResponseEntity<Task> getTask(@PathVariable("id") Integer id) {
        Optional<Task> task = this.taskServices.findTask(id);
        if (task.isPresent()) {
            return new ResponseEntity<Task>(task.get(),HttpStatus.OK);
        }   else {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }  
    }

    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task taskRespond = this.taskServices.create(task);
        return new ResponseEntity<Task>(taskRespond,HttpStatus.OK); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> modifyTask(@PathVariable("id") Integer id, @RequestBody Task task) {
        HttpStatus httpStatus = this.taskServices.modify(id, task);
        return new ResponseEntity<Task>(httpStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Integer id) {
        this.taskServices.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
