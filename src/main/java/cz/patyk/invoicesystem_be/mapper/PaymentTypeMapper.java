package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.PaymentTypeDto;
import cz.patyk.invoicesystem_be.entities.PaymentType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentTypeMapper {
    PaymentTypeDto toDto(PaymentType paymentType);

    PaymentType toEntity(PaymentTypeDto paymentTypeDto);
}
