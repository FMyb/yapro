package org.example.dto;

import javax.validation.constraints.NotBlank;

public class CreateParticipantRequest {
    @NotBlank
    private String name;
    private String wish;

    public CreateParticipantRequest(String name, String wish) {
        this.name = name;
        this.wish = wish;
    }

    public CreateParticipantRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }
}
