package com.pedromolon.ToDo.service;

import com.pedromolon.ToDo.DTO.request.TaskRequest;
import com.pedromolon.ToDo.DTO.response.TaskResponse;
import com.pedromolon.ToDo.entity.Task;
import com.pedromolon.ToDo.entity.User;
import com.pedromolon.ToDo.exception.ResourceNotFoundException;
import com.pedromolon.ToDo.mapper.TaskMapper;
import com.pedromolon.ToDo.repository.TaskRepository;
import com.pedromolon.ToDo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper,
                       UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userRepository = userRepository;
    }

    public List<TaskResponse> getTasksByUser(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream()
                .map(TaskMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(TaskMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<TaskResponse> getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(TaskMapper::toResponse);
    }

    public Optional<TaskResponse> createTask(Long userId, TaskRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        Task task = TaskMapper.toEntity(request, user);
        Task savedTask = taskRepository.save(task);
        return Optional.of(TaskMapper.toResponse(savedTask));
    }

    public Optional<TaskResponse> updateTask(Long id, TaskRequest request) {
        return taskRepository.findById(id).map(existingTask -> {
            existingTask.setTitle(request.getTitle());
            existingTask.setDescription(request.getDescription());
            existingTask.setCompleted(request.isCompleted());

            Task updatedTask = taskRepository.save(existingTask);
            return taskMapper.toResponse(updatedTask);
        });
    }

    public boolean deleteTask(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.delete(task);
                    return true;
                }).orElseThrow(() -> new ResourceNotFoundException("A tarefa com o ID: " + id + " não foi encontrada"));
    }

}
