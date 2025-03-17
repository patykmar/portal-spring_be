package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import cz.patyk.invoicesystem_be.dto.InvoiceDto;
import cz.patyk.invoicesystem_be.views.MainLayout;

@Route(value = "invoice", layout = MainLayout.class)
public class InvoiceView extends VerticalLayout {
    public InvoiceView() {
    }

    Grid<InvoiceDto> invoiceDtoGrid() {
        Grid<InvoiceDto> grid = new Grid<>(InvoiceDto.class, false);
        grid.addColumn(InvoiceDto::getId).setHeader("ID");
return grid;
    }
}
