package org.example.dto;

import java.util.List;

public class GetGroupResponse {
    private long id;
    private String name;
    private String description;
    private List<ParticipantDto> participantDtos;

    public GetGroupResponse(long id, String name, String description, List<ParticipantDto> participantDtos) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.participantDtos = participantDtos;
    }

    public GetGroupResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<ParticipantDto> getParticipants() {
        return participantDtos;
    }

    public void setParticipants(List<ParticipantDto> participantDtos) {
        this.participantDtos = participantDtos;
    }
}
