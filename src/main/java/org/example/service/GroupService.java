package org.example.service;

import org.example.dto.CreateGroupRequest;
import org.example.dto.GetGroupResponse;
import org.example.dto.GetGroupsResponse;
import org.example.dto.UpdateGroupRequest;

import java.util.List;

public interface GroupService {
    long saveGroupService(CreateGroupRequest createGroupRequest);

    List<GetGroupsResponse> getGroups();

    GetGroupResponse getGroup(long id);

    void updateGroup(long id, UpdateGroupRequest updateGroupRequest);

    void deleteGroup(long id);
}
