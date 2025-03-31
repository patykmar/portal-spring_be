package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.TicketTypeDto;
import cz.patyk.invoicesystem_be.service.TicketTypeService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "ticket-type", layout = MainLayout.class)
public class TicketTypeView extends VerticalLayout {
    private final TicketTypeService ticketTypeService;

    public TicketTypeView(TicketTypeService ticketTypeService) {
        this.ticketTypeService = ticketTypeService;
        add(ticketTypeDtoGrid());
    }

    Grid<TicketTypeDto> ticketTypeDtoGrid() {
        Grid<TicketTypeDto> grid = new Grid<>(TicketTypeDto.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(TicketTypeDto::getId).setHeader("ID");
        grid.addColumn(TicketTypeDto::getName).setHeader("Name");
        grid.addColumn(TicketTypeDto::getAbbreviation).setHeader("Abbreviation");
        grid.addColumn(TicketTypeDto::isDisable).setHeader("is disable?");
        grid.addColumn(TicketTypeDto::getCoefficientPrice).setHeader("Coefficient price");
        grid.addColumn(TicketTypeDto::getCoefficientTime).setHeader("Coefficient time");
        grid.setItems(ticketTypeService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
