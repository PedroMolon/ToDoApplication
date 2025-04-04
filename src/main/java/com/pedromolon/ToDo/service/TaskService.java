package com.pedromolon.ToDo.service;

import com.pedromolon.ToDo.DTO.TaskDTO;
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

    public List<TaskDTO> getTasksByUser(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toList());
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

    public Optional<TaskDTO> createTask(Long userId, TaskDTO taskDTO) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Task task = taskMapper.toEntity(taskDTO);
            task.setUser(user.get());
            Task savedTask = taskRepository.save(task);
            return Optional.of(TaskMapper.toDTO(savedTask));
        }

        return Optional.empty();
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
                }).orElseThrow(() -> new ResourceNotFoundException("A tarefa com o ID: " + id + " não foi encontrada"));
    }

}
