package org.example.service;

import org.example.dto.CreateParticipantRequest;
import org.example.dto.ParticipantDto;

import java.util.List;

public interface ParticipantService {
    long saveParticipant(long groupId, CreateParticipantRequest createParticipantRequest);

    void deleteParticipant(long groupId, long participantId);

    List<ParticipantDto> toss(long groupId);

    ParticipantDto.Recipient getRecipient(long groupId, long participantId);
}
