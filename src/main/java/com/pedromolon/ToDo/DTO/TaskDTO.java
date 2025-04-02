package com.pedromolon.ToDo.DTO;

import com.pedromolon.ToDo.entity.User;

public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private User user;

    public TaskDTO() {
    }

    public TaskDTO(Long id, User user, boolean completed, String title, String description) {
        this.id = id;
        this.user = user;
        this.completed = completed;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
