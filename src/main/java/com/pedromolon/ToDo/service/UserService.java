package com.pedromolon.ToDo.service;

import com.pedromolon.ToDo.DTO.request.UserRequest;
import com.pedromolon.ToDo.DTO.response.UserResponse;
import com.pedromolon.ToDo.entity.User;
import com.pedromolon.ToDo.exception.ResourceNotFoundException;
import com.pedromolon.ToDo.mapper.UserMapper;
import com.pedromolon.ToDo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<UserResponse> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserMapper::toResponse);
    }

    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));

        User saveUser = userRepository.save(user);
        return userMapper.toResponse(saveUser);
    }

    public Optional<UserResponse> updateUser(Long id, UserRequest request) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(request.getName());
            existingUser.setEmail(request.getEmail());

            User updatedUser = userRepository.save(existingUser);
            return userMapper.toResponse(updatedUser);
        });
    }

    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                }).orElseThrow(() -> new ResourceNotFoundException("Usuário com ID: " + id + " não encontrado"));
    }

}
