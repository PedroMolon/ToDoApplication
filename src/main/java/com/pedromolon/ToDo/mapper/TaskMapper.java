package com.pedromolon.ToDo.mapper;

import com.pedromolon.ToDo.DTO.request.TaskRequest;
import com.pedromolon.ToDo.DTO.response.TaskResponse;
import com.pedromolon.ToDo.entity.Task;
import com.pedromolon.ToDo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public static Task toEntity(TaskRequest request, User user) {
        if (request == null || user == null) {
            return null;
        }

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(request.isCompleted());
        task.setUser(user);

        return task;
    }

    public static TaskResponse toResponse(Task task) {
        if (task == null) {
            return null;
        }

        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setCompleted(task.isCompleted());

        return response;
    }

}
