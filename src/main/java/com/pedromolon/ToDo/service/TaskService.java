package com.pedromolon.ToDo.service;

import com.pedromolon.ToDo.DTO.TaskDTO;
import com.pedromolon.ToDo.entity.Task;
import com.pedromolon.ToDo.mapper.TaskMapper;
import com.pedromolon.ToDo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<TaskDTO> getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(TaskMapper::toDTO);
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDTO(savedTask);
    }

    public Optional<TaskDTO> updateTask(Long id, TaskDTO taskDTO) {
        return taskRepository.findById(id).map(existingTask -> {
            existingTask.setTitle(taskDTO.getTitle());
            existingTask.setDescription(taskDTO.getDescription());
            existingTask.setCompleted(taskDTO.isCompleted());

            Task updatedTask = taskRepository.save(existingTask);
            return taskMapper.toDTO(updatedTask);
        });
    }

    public boolean deleteTask(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.delete(task);
                    return true;
                }).orElse(false);
    }

}
