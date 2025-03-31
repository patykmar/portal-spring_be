package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.InfluencingTicketDto;
import cz.patyk.invoicesystem_be.service.InfluencingTicketService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "influencing-ticket", layout = MainLayout.class)
public class InfluencingTicketView extends VerticalLayout {
    private final InfluencingTicketService influencingTicketService;

    public InfluencingTicketView(InfluencingTicketService influencingTicketService) {
        this.influencingTicketService = influencingTicketService;
        add(influencingTicket());
    }

    Grid<InfluencingTicketDto> influencingTicket() {
        Grid<InfluencingTicketDto> grid = new Grid<>(InfluencingTicketDto.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(InfluencingTicketDto::getId).setHeader("Id");
        grid.addColumn(InfluencingTicketDto::getName).setHeader("Name");
        grid.addColumn(InfluencingTicketDto::isForPriority).setHeader("is for Priority?");
        grid.addColumn(InfluencingTicketDto::isForImpact).setHeader("is for Impact?");
        grid.addColumn(InfluencingTicketDto::getCoefficientPrice).setHeader("Coefficient price");
        grid.addColumn(InfluencingTicketDto::getCoefficientTime).setHeader("Coefficient time");
        grid.setItems(influencingTicketService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
