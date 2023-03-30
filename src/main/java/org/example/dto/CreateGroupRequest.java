package org.example.dto;


import javax.validation.constraints.NotBlank;

public class CreateGroupRequest {
    @NotBlank
    private String name;

    private String description;

    public CreateGroupRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
