package com.pedromolon.ToDo.mapper;

import com.pedromolon.ToDo.DTO.UserDTO;
import com.pedromolon.ToDo.entity.User;

import java.util.stream.Collectors;

public class UserMapper {

    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());

        if (user.getTasks() != null) {
            userDTO.setTasks(user.getTasks().stream()
                    .map(TaskMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        return user;
    }

}
