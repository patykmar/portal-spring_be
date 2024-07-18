package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.out.PaymentTypeDtoOut;
import cz.patyk.invoicesystem_be.dto.in.PaymentTypeDtoIn;
import cz.patyk.invoicesystem_be.entities.PaymentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PaymentTypeMapper {
    @Mapping(source = "default", target = "isDefault")
    PaymentTypeDtoOut toDto(PaymentType paymentType);

    @Mapping(source = "default", target = "isDefault")
    PaymentType toEntity(PaymentTypeDtoOut paymentTypeDtoOut);

    @Mapping(source = "default", target = "isDefault")
    PaymentType toEntity(PaymentTypeDtoIn paymentTypeDtoIn);
}
