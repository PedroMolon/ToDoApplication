package com.pedromolon.ToDo.mapper;

import com.pedromolon.ToDo.DTO.request.UserRequest;
import com.pedromolon.ToDo.DTO.response.UserResponse;
import com.pedromolon.ToDo.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static User toEntity(UserRequest request) {
        if (request == null) {
            return null;
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return user;
    }

    public static UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setTasks(user.getTasks().stream()
                .map(TaskMapper::toResponse)
                .collect(Collectors.toList()));

        return response;
    }

}
