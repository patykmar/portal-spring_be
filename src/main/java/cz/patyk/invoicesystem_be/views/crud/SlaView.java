package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.out.SlaDtoOut;
import cz.patyk.invoicesystem_be.service.SlaService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "sla", layout = MainLayout.class)
public class SlaView extends VerticalLayout {
    private final SlaService slaService;

    public SlaView(SlaService slaService) {
        this.slaService = slaService;
        add(slaDtoGrid());
    }

    Grid<SlaDtoOut> slaDtoGrid() {
        Grid<SlaDtoOut> grid = new Grid<>(SlaDtoOut.class, false);
        grid.addColumn(SlaDtoOut::getId).setHeader("Id");
        grid.addColumn(SlaDtoOut::getTariffId).setHeader("Tariff Id");
        grid.addColumn(SlaDtoOut::getPriorityId).setHeader("Priority Id");
        grid.addColumn(SlaDtoOut::getTicketTypeId).setHeader("Ticket type");
        grid.addColumn(SlaDtoOut::getReactionTime).setHeader("Reaction time");
        grid.addColumn(SlaDtoOut::getResolvedTime).setHeader("Resolved time");
        grid.addColumn(SlaDtoOut::getPriceMultiplier).setHeader("Price multiplier");
        grid.setItems(slaService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
