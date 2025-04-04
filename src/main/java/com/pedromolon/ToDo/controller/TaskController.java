package com.pedromolon.ToDo.controller;

import com.pedromolon.ToDo.DTO.TaskDTO;
import com.pedromolon.ToDo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todoapplication/users/{userId}/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasksByUser(@PathVariable Long userId) {
        List<TaskDTO> tasks = taskService.getTasksByUser(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long taskId) {
        Optional<TaskDTO> task = taskService.getTaskById(taskId);
        return task.map(taskDTO -> new ResponseEntity<>(taskDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@PathVariable Long userId, @Valid @RequestBody TaskDTO taskDTO) {
        Optional<TaskDTO> created = taskService.createTask(userId, taskDTO);
        return created.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @Valid @RequestBody TaskDTO taskDTO) {
        Optional<TaskDTO> updated = taskService.updateTask(taskId, taskDTO);
        return updated.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        boolean deleted = taskService.deleteTask(taskId);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
