package cz.patyk.invoicesystem_be.mapper;

import cz.patyk.invoicesystem_be.dto.in.WorkInventoryDtoIn;
import cz.patyk.invoicesystem_be.dto.out.WorkInventoryDtoOut;
import cz.patyk.invoicesystem_be.entities.Company;
import cz.patyk.invoicesystem_be.entities.Tariff;
import cz.patyk.invoicesystem_be.entities.User;
import cz.patyk.invoicesystem_be.entities.WorkInventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {CommonMapper.class, CompanyMapper.class, UserMapper.class, TariffMapper.class})
public interface WorkInventoryMapper {
    @Mapping(target = "id", source = "workInventoryDtoIn.id")
    @Mapping(target = "description", source = "workInventoryDtoIn.description")
    @Mapping(target = "workStart", source = "workInventoryDtoIn.workStart", qualifiedByName = "timestampToDate")
    @Mapping(target = "workEnd", source = "workInventoryDtoIn.workEnd", qualifiedByName = "timestampToDate")
    @Mapping(target = "tariff", source = "tariff")
    @Mapping(target = "company", source = "company")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "invoice", ignore = true)
    WorkInventory toEntity(WorkInventoryDtoIn workInventoryDtoIn, Tariff tariff, Company company, User user);

    @Mapping(target = "links", ignore = true)
    @Mapping(target = "invoiceId", source = "invoice.id")
    WorkInventoryDtoOut toDtoOut(WorkInventory workInventory);
}
