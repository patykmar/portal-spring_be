package cz.patyk.invoicesystem_be.convertor;

import cz.patyk.invoicesystem_be.dto.in.WorkInventoryDtoIn;
import cz.patyk.invoicesystem_be.dto.out.WorkInventoryDtoOut;
import cz.patyk.invoicesystem_be.entities.WorkInventory;
import cz.patyk.invoicesystem_be.mapper.WorkInventoryMapper;
import cz.patyk.invoicesystem_be.service.CompanyService;
import cz.patyk.invoicesystem_be.service.TariffService;
import cz.patyk.invoicesystem_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkInventoryConverter implements CrudConvertor<WorkInventoryDtoIn, WorkInventoryDtoOut, WorkInventory> {
    private final TariffService tariffService;
    private final CompanyService companyService;
    private final UserService userService;
    private final WorkInventoryMapper workInventoryMapper;

    @Override
    public WorkInventory inputToEntity(WorkInventoryDtoIn userInput) {
        var tariff = tariffService.getOneEntity(userInput.getTariffId());
        var company = companyService.getOneEntity(userInput.getCompanyId());
        var user = userService.getOneEntity(userInput.getTariffId());
        return workInventoryMapper.toEntity(userInput, tariff, company, user);
    }

    @Override
    public WorkInventoryDtoOut entityToDto(WorkInventory entity) {
        return workInventoryMapper.toDtoOut(entity);
    }
}
