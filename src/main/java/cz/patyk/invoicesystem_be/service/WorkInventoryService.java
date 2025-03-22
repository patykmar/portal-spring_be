package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.WorkInventoryDto;
import cz.patyk.invoicesystem_be.entities.WorkInventory;
import cz.patyk.invoicesystem_be.mapper.WorkInventoryMapper;
import cz.patyk.invoicesystem_be.repositories.WorkInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkInventoryService implements CrudService<WorkInventoryDto, WorkInventoryDto, WorkInventory> {
    private final WorkInventoryRepository workInventoryRepository;
    private final WorkInventoryMapper workInventoryMapper;
    private final ErrorHandleService errorHandleService;

    @Override
    public List<WorkInventoryDto> getAll(Pageable pageable) {
        return workInventoryRepository.findAll(pageable)
                .stream()
                .map(workInventoryMapper::toDto)
                .toList();
    }

    @Override
    public WorkInventoryDto getOne(Long id) {
        return workInventoryMapper.toDto(getOneEntity(id));
    }

    @Override
    public WorkInventory getOneEntity(Long id) {
        return workInventoryRepository.findById(id)
                .orElseThrow(() -> errorHandleService.handleNotFoundError(id, ServiceConstants.WORK_INVENTORY_NOT_FOUND_MESSAGE));
    }

    @Override
    public WorkInventoryDto newItem(WorkInventoryDto dtoIn) {
        WorkInventory workInventory = workInventoryMapper.toEntity(dtoIn);
        return workInventoryMapper.toDto(workInventoryRepository.save(workInventory));
    }

    @Override
    public WorkInventoryDto editItem(WorkInventoryDto dtoIn, Long id) {
        var entityFromDb = getOneEntity(id);
        var entityFromDto = workInventoryMapper.toEntity(dtoIn);
        entityFromDto.setId(id);
        return workInventoryMapper.toDto(workInventoryRepository.save(entityFromDto));
    }

    @Override
    public void deleteItem(Long id) {
        var entityFromDb = getOneEntity(id);
        workInventoryRepository.deleteById(id);
    }
}
