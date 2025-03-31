package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import cz.patyk.invoicesystem_be.dto.out.InvoiceDtoOut;
import cz.patyk.invoicesystem_be.views.MainLayout;

@Route(value = "invoice", layout = MainLayout.class)
public class InvoiceView extends VerticalLayout {
    public InvoiceView() {
    }

    Grid<InvoiceDtoOut> invoiceDtoGrid() {
        Grid<InvoiceDtoOut> grid = new Grid<>(InvoiceDtoOut.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(InvoiceDtoOut::getId).setHeader("ID");
        return grid;
    }
}
