package cz.patyk.invoicesystem_be.dto.in;

import lombok.Data;

@Data
public class QueueUserDtoIn {
    private Long queue;
    private Long user;
}
