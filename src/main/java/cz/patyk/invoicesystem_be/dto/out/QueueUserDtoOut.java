package cz.patyk.invoicesystem_be.dto.out;

import lombok.Data;

@Data
public class QueueUserDtoOut {
    private Long id;
    private QueueDtoOut queueDto;
    private UserDtoOut userDto;
}
