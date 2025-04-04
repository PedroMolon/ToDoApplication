package com.pedromolon.ToDo.DTO;

import com.pedromolon.ToDo.entity.Task;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;

    private List<TaskDTO> tasks;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email, List<TaskDTO> tasks) {
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

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

}
