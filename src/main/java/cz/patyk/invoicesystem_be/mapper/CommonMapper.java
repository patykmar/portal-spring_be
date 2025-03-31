package cz.patyk.invoicesystem_be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.Instant;
import java.util.Date;

@Mapper
public interface CommonMapper {
    @Named("timestampToDate")
    default Date toDate(Long timestamp) {
        return Date.from(Instant.ofEpochSecond(timestamp));
    }
}
