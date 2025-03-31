package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.out.VatDtoOut;
import cz.patyk.invoicesystem_be.service.VatService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@Route(value = "vat", layout = MainLayout.class)
public class VatView extends VerticalLayout {
    private final VatService vatService;

    @Autowired
    public VatView(VatService vatService) {
        this.vatService = vatService;
        add(vatGrid());
    }

    Grid<VatDtoOut> vatGrid() {
        Grid<VatDtoOut> grid = new Grid<>(VatDtoOut.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(VatDtoOut::getId).setHeader("ID");
        grid.addColumn(VatDtoOut::getName).setHeader("Name");
        grid.addColumn(VatDtoOut::isDefault).setHeader("is default");
        grid.addColumn(VatDtoOut::getPercent).setHeader("Percent");
        grid.addColumn(VatDtoOut::getMultiplier).setHeader("Multiplier");
        grid.setItems(vatService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
