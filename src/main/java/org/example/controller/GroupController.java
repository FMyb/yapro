package org.example.controller;


import org.example.dto.CreateGroupRequest;
import org.example.dto.GetGroupResponse;
import org.example.dto.GetGroupsResponse;
import org.example.dto.UpdateGroupRequest;
import org.example.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping(value = "/group", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createGroup(@Validated @RequestBody CreateGroupRequest createGroupRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.saveGroupService(createGroupRequest));
    }

    @GetMapping(value = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetGroupsResponse> getGroups() {
        return groupService.getGroups();
    }

    @GetMapping(value = "/group/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetGroupResponse getGroup(@PathVariable("id") long id) {
        return groupService.getGroup(id);
    }

    @PutMapping(value = "/group/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateGroup(@PathVariable("id") long id, @Validated @RequestBody UpdateGroupRequest updateGroupRequest) {
        groupService.updateGroup(id, updateGroupRequest);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(value = "/group/{id}")
    public void deleteGroup(@PathVariable("id") long id) {
        groupService.deleteGroup(id);
    }
}
