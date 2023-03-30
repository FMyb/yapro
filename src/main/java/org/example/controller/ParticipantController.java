package org.example.controller;

import org.example.dto.CreateParticipantRequest;
import org.example.dto.ParticipantDto;
import org.example.service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class ParticipantController {
    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping(value = "/{id}/participant", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> saveParticipant(
            @PathVariable("id") long groupId,
            @Validated @RequestBody CreateParticipantRequest createParticipantRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(participantService.saveParticipant(groupId, createParticipantRequest));
    }

    @DeleteMapping(value = "/{groupId}/participant/{participantId}")
    public void deleteParticipant(@PathVariable("groupId") long groupId, @PathVariable("participantId") long participantId) {
        participantService.deleteParticipant(groupId, participantId);
    }

    @PostMapping(value = "/{id}/toss", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ParticipantDto> toss(@PathVariable("id") long groupId) {
        return participantService.toss(groupId);
    }

    @GetMapping(value = "/{groupId}/participant/{participantId}/recipient")
    public ParticipantDto.Recipient getRecipient(
            @PathVariable("groupId") long groupId,
            @PathVariable("participantId") long participantId
    ) {
        return participantService.getRecipient(groupId, participantId);
    }
}
