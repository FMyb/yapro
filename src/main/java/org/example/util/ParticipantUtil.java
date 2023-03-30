package org.example.util;

import org.example.dto.ParticipantDto;
import org.example.model.Participant;

public class ParticipantUtil {
       public static ParticipantDto toDto(Participant participant, Participant recipient) {
              return new ParticipantDto(
                      participant.getId(),
                      participant.getName(),
                      participant.getWish(),
                      recipient == null ? null : new ParticipantDto.Recipient(
                              recipient.getId(),
                              recipient.getName(),
                              recipient.getWish()
                      ));
       }
}
