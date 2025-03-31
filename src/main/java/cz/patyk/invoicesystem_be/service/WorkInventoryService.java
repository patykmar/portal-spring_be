package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.convertor.WorkInventoryConverter;
import cz.patyk.invoicesystem_be.dto.in.WorkInventoryDtoIn;
import cz.patyk.invoicesystem_be.dto.out.WorkInventoryDtoOut;
import cz.patyk.invoicesystem_be.entities.WorkInventory;
import cz.patyk.invoicesystem_be.repositories.WorkInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkInventoryService implements CrudService<WorkInventoryDtoIn, WorkInventoryDtoOut, WorkInventory> {
    private final WorkInventoryRepository workInventoryRepository;
    private final ErrorHandleService errorHandleService;
    private final WorkInventoryConverter workInventoryConverter;

    @Override
    public List<WorkInventoryDtoOut> getAll(Pageable pageable) {
        return workInventoryRepository.findAll(pageable)
                .stream()
                .map(workInventoryConverter::entityToDto)
                .toList();
    }

    @Override
    public WorkInventoryDtoOut getOne(Long id) {
        return workInventoryConverter.entityToDto(getOneEntity(id));
    }

    @Override
    public WorkInventory getOneEntity(Long id) {
        return workInventoryRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, ServiceConstants.WORK_INVENTORY_NOT_FOUND_MESSAGE));
    }

    @Override
    public WorkInventoryDtoOut newItem(WorkInventoryDtoIn dtoIn) {
        WorkInventory workInventory = workInventoryConverter.inputToEntity(dtoIn);
        return workInventoryConverter.entityToDto(workInventoryRepository.save(workInventory));
    }

    @Override
    public WorkInventoryDtoOut editItem(WorkInventoryDtoIn dtoIn, Long id) {
        var entityFromDb = getOneEntity(id);
        var entityFromDto = workInventoryConverter.inputToEntity(dtoIn);
        entityFromDto.setId(entityFromDb.getId());
        return workInventoryConverter.entityToDto(workInventoryRepository.save(entityFromDto));
    }

    @Override
    public void deleteItem(Long id) {
        var entityFromDb = getOneEntity(id);
        workInventoryRepository.delete(entityFromDb);
    }
}
