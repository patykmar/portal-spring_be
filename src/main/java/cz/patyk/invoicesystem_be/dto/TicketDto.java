package cz.patyk.invoicesystem_be.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class TicketDto {
    private Long id;
    private Long ciId;
    private Long queueUserId;
    private Long userCreatedId;
    private Long userResolvedId;
    private Long parentTicketId;
    private Long workInventoryId;
    private Long ticketCloseStateId;
    private Long ticketStateId;
    private Long ticketTypeId;
    private Long priorityId;
    private Long impactId;
    private String descriptionTitle;
    private String descriptionBody;
    private String closedNotes;
    private Date closedDatetime;
    private Date reactionDatetime;
    private Date resolveDatetime;
    private Date createdDatetime;
    private String toString;
}