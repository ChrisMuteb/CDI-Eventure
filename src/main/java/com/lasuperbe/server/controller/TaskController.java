package com.lasuperbe.server.controller;

import com.lasuperbe.server.entity.Task;
import com.lasuperbe.server.service.Impl.TaskService;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    private static final Logger logger = Logger.getLogger(TaskController.class);
    {
        BasicConfigurator.configure();
    }

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(){
        List<Task> tasks = taskService.findAllTask();
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable int id){
        Task task = taskService.findTaskById(id);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Task> saveTask(@RequestBody Task task){
         Task result = taskService.addTask(task);
         if(result == null) return new ResponseEntity<Task>(HttpStatus.CONFLICT);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Task>(headers, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestParam(name = "status") String status){
        Task updatedTask = taskService.updateTaskRole(id, status);

        return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
        return new ResponseEntity<String>("Task deleted" + id, HttpStatus.NO_CONTENT);
    }
}
