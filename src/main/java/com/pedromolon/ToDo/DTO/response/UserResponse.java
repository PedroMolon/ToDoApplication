package com.pedromolon.ToDo.DTO.response;

import java.util.List;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private List<TaskResponse> tasks;

    public UserResponse() {
    }

    public UserResponse(Long id, String name, String email, List<TaskResponse> tasks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TaskResponse> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResponse> tasks) {
        this.tasks = tasks;
    }
}
