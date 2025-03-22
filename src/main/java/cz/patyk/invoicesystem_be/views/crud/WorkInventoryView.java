package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.WorkInventoryDto;
import cz.patyk.invoicesystem_be.service.WorkInventoryService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "workInvertory", layout = MainLayout.class)
public class WorkInventoryView extends VerticalLayout {
    private final WorkInventoryService workInventoryService;

    public WorkInventoryView(WorkInventoryService workInventoryService) {
        this.workInventoryService = workInventoryService;
        add(workInventoryGrid());
    }

    Grid<WorkInventoryDto> workInventoryGrid() {
        Grid<WorkInventoryDto> grid = new Grid<>(WorkInventoryDto.class, false);
        grid.addColumn(WorkInventoryDto::getId).setHeader("ID");
        grid.addColumn(WorkInventoryDto::getTariffId).setHeader("Tariff ID");
        grid.addColumn(WorkInventoryDto::getCompanyId).setHeader("Company ID");
        grid.addColumn(WorkInventoryDto::getWorkStart).setHeader("Work start");
        grid.addColumn(WorkInventoryDto::getWorkEnd).setHeader("Work end");
        grid.setItems(workInventoryService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
