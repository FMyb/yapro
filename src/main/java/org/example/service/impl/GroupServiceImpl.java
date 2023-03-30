package org.example.service.impl;

import org.example.dto.*;
import org.example.model.Group;
import org.example.model.Participant;
import org.example.repository.GroupRepository;
import org.example.service.GroupService;
import org.example.util.ParticipantUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public long saveGroupService(CreateGroupRequest createGroupRequest) {
        Group group = new Group();
        group.setName(createGroupRequest.getName());
        group.setDescription(createGroupRequest.getDescription());
        group = groupRepository.save(group);
        return group.getId();
    }

    @Override
    public List<GetGroupsResponse> getGroups() {
        return groupRepository.findAll().stream()
                .map(it -> new GetGroupsResponse(it.getId(), it.getName(), it.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GetGroupResponse getGroup(long id) {
        Group group = groupRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("group %d not found", id))
        );
        var x = group.getParticipants();
        GetGroupResponse getGroupResponse = new GetGroupResponse();
        getGroupResponse.setId(group.getId());
        getGroupResponse.setName(group.getName());
        getGroupResponse.setDescription(group.getDescription());
        getGroupResponse.setParticipants(group.getParticipants().stream()
                .map(it -> ParticipantUtil.toDto(it, it.getRecipient()))
                .collect(Collectors.toList())
        );
        return getGroupResponse;
    }

    @Override
    @Transactional
    public void updateGroup(long id, UpdateGroupRequest updateGroupRequest) {
        Group group = groupRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("group %d not found", id))
        );
        group.setName(updateGroupRequest.getName());
        group.setDescription(updateGroupRequest.getDescription());
        groupRepository.save(group);
    }

    @Override
    @Transactional
    public void deleteGroup(long id) {
        groupRepository.findById(id).ifPresent(groupRepository::delete);
    }
}
