package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.PaymentTypeDto;
import cz.patyk.invoicesystem_be.dto.in.PaymentTypeDtoIn;
import cz.patyk.invoicesystem_be.entities.PaymentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentTypeMapper {
    @Mapping(source = "default", target = "isDefault")
    PaymentTypeDto toDto(PaymentType paymentType);

    @Mapping(source = "default", target = "isDefault")
    PaymentType toEntity(PaymentTypeDto paymentTypeDto);

    @Mapping(source = "default", target = "isDefault")
    PaymentType toEntity(PaymentTypeDtoIn paymentTypeDtoIn);
}
