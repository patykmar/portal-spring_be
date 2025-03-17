package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.out.TariffDtoOut;
import cz.patyk.invoicesystem_be.service.TariffService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "tariff", layout = MainLayout.class)
public class TariffView extends VerticalLayout {
    private final TariffService tariffService;

    public TariffView(TariffService tariffService) {
        this.tariffService = tariffService;
        add(tariffDtoOutGrid());
    }

    Grid<TariffDtoOut> tariffDtoOutGrid() {
        Grid<TariffDtoOut> grid = new Grid<>(TariffDtoOut.class, false);
        grid.addColumn(TariffDtoOut::getId).setHeader("ID");
        grid.addColumn(TariffDtoOut::getVatDto).setHeader("VAT");
        grid.addColumn(TariffDtoOut::getName).setHeader("Name");
        grid.addColumn(TariffDtoOut::getPrice).setHeader("Price");
        grid.setItems(tariffService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
