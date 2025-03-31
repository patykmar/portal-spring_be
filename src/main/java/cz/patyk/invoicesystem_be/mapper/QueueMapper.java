package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.QueueDtoIn;
import cz.patyk.invoicesystem_be.dto.out.QueueDtoOut;
import cz.patyk.invoicesystem_be.entities.Queue;
import org.mapstruct.Mapper;

@Mapper
public interface QueueMapper {
    Queue toEntity(QueueDtoIn queueDto);

    QueueDtoOut toDto(Queue queue);
}
