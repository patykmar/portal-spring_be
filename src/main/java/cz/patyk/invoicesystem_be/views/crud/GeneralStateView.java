package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.GeneralStateDto;
import cz.patyk.invoicesystem_be.service.GeneralStateService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "general-state", layout = MainLayout.class)
public class GeneralStateView extends VerticalLayout {
    private final GeneralStateService generalStateService;

    public GeneralStateView(GeneralStateService generalStateService) {
        this.generalStateService = generalStateService;
        add(generalStateGrid());
    }

    Grid<GeneralStateDto> generalStateGrid() {
        Grid<GeneralStateDto> grid = new Grid<>(GeneralStateDto.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(GeneralStateDto::getId).setHeader("ID");
        grid.addColumn(GeneralStateDto::getName).setHeader("Name");
        grid.addColumn(GeneralStateDto::getCoefficientPrice).setHeader("Coefficient price");
        grid.addColumn(GeneralStateDto::isForTicket).setHeader("is for Ticket?");
        grid.addColumn(GeneralStateDto::isForCi).setHeader("is for CI?");
        grid.addColumn(GeneralStateDto::isForCloseState).setHeader("is for Close State?");
        grid.setItems(generalStateService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
