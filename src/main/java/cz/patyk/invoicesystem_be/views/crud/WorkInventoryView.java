package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.out.WorkInventoryDtoOut;
import cz.patyk.invoicesystem_be.service.WorkInventoryService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "work-inventory", layout = MainLayout.class)
public class WorkInventoryView extends VerticalLayout {
    private final WorkInventoryService workInventoryService;

    public WorkInventoryView(WorkInventoryService workInventoryService) {
        this.workInventoryService = workInventoryService;
        add(workInventoryGrid());
    }

    Grid<WorkInventoryDtoOut> workInventoryGrid() {
        Grid<WorkInventoryDtoOut> grid = new Grid<>(WorkInventoryDtoOut.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(WorkInventoryDtoOut::getId).setHeader("ID");
        grid.addColumn(WorkInventoryDtoOut::getTariff).setHeader("Tariff ID");
        grid.addColumn(WorkInventoryDtoOut::getCompany).setHeader("Company ID");
        grid.addColumn(WorkInventoryDtoOut::getWorkStart).setHeader("Work start");
        grid.addColumn(WorkInventoryDtoOut::getWorkEnd).setHeader("Work end");
        grid.setItems(workInventoryService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
