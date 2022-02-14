package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.QueueDto;
import cz.patyk.invoicesystem_be.entities.Queue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QueueMapper {
    Queue toEntity(QueueDto queueDto);

    QueueDto toDto(Queue queue);
}
