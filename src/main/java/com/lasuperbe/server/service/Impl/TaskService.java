package com.lasuperbe.server.service.Impl;

import com.lasuperbe.server.entity.Task;
import com.lasuperbe.server.repository.TaskRepository;
import com.lasuperbe.server.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllTask(){
        return taskRepository.findAll();
    }
    public Task addTask(Task task){
        Optional<Task> existingTask = taskRepository.findTaskByTitle(task.getTitle());
        if(existingTask.isPresent())
            throw new RuntimeException();
        return taskRepository.save(task);
    }

    public Task findTaskById(Integer taskID){
        Optional<Task> task = taskRepository.findById(taskID);
        if(task.isEmpty())
            throw new RuntimeException();
        Task result = task.get();
        return result;
    }

    public Task updateTaskRole(Integer taskID, String status){
        Task existingTask = findTaskById(taskID);
        existingTask.setStatus(status);
        return taskRepository.save(existingTask);
    }
    public void deleteTask(Integer taskID){
        Task existingTask = findTaskById(taskID);
        taskRepository.delete(existingTask);
    }
}
