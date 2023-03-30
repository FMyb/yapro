package org.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "group")
    private List<Participant> participants;

    public Group(long id, String name, String description, List<Participant> participants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.participants = participants;
    }

    public Group() {
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

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @PreRemove
    private void preRemove() {
        participants.forEach(it -> it.setGroup(null));
    }
}
