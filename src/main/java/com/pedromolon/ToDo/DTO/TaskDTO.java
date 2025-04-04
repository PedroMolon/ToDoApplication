package com.pedromolon.ToDo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskDTO {

    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 200, message = "O título deve ter entre 3 e 200 caracteres")
    private String title;

    @Size(min = 3, max = 500, message = "A descrição deve ter entre 3 e 500 caracteres")
    private String description;
    private boolean completed;
    private Long userId;

    public TaskDTO() {
    }

    public TaskDTO(Long id, String title, String description, boolean completed, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
