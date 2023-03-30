package org.example.service.impl;


import org.example.dto.CreateParticipantRequest;
import org.example.dto.ParticipantDto;
import org.example.model.Group;
import org.example.model.Participant;
import org.example.repository.GroupRepository;
import org.example.repository.ParticipantRepository;
import org.example.service.ParticipantService;
import org.example.util.ParticipantUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    private static final int TOSS_LIMIT = 3;

    private final ParticipantRepository participantRepository;
    private final GroupRepository groupRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository, GroupRepository groupRepository) {
        this.participantRepository = participantRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    @Transactional
    public long saveParticipant(long groupId, CreateParticipantRequest createParticipantRequest) {
        Group group = groupRepository.findById(groupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("group %d not found", groupId))
        );
        Participant participant = new Participant();
        participant.setName(createParticipantRequest.getName());
        participant.setWish(createParticipantRequest.getWish());
        participant.setGroup(group);
        participant = participantRepository.save(participant);
        return participant.getId();
    }

    @Override
    @Transactional
    public void deleteParticipant(long groupId, long participantId) {
        Group group = groupRepository.findById(groupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("group %d not found", groupId))
        );

        participantRepository.findById(participantId).ifPresent(it -> {
            if (group.getId() != it.getGroup().getId()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("group %d don't have participant %d", groupId, participantId));
            }
            participantRepository.delete(it);
        });
    }

    @Override
    @Transactional
    public List<ParticipantDto> toss(long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("group %d not found", groupId))
        );
        List<Participant> participants = group.getParticipants();
        if (participants.size() < TOSS_LIMIT) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("group %d doesn't have enough members for toss", groupId));
        }
        participants.get(0).setRecipient(participants.get(participants.size() - 1));
        for (int i = 1; i < participants.size(); i++) {
            participants.get(i).setRecipient(participants.get(i - 1));
        }
        participants = participantRepository.saveAll(participants);
        return participants.stream()
                .map(it -> ParticipantUtil.toDto(it, it.getRecipient())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ParticipantDto.Recipient getRecipient(long groupId, long participantId) {
        Group group = groupRepository.findById(groupId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("group %d not found", groupId))
        );
        Participant participant = participantRepository.findById(participantId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("participant %d not found", participantId))
        );
        if (group.getId() != participant.getGroup().getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("group %d don't have participant %d", groupId, participantId));
        }
        Participant recipient = participant.getRecipient();
        if (recipient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("recipient for participant %d not found", participantId));
        }
        ParticipantDto.Recipient recipientDto = new ParticipantDto.Recipient();
        recipientDto.setId(recipient.getId());
        recipientDto.setName(recipient.getName());
        recipientDto.setWish(recipient.getWish());
        return recipientDto;
    }
}
