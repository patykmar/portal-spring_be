package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import cz.patyk.invoicesystem_be.dto.out.InvoiceDtoOut;
import cz.patyk.invoicesystem_be.service.InvoiceService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "invoice", layout = MainLayout.class)
public class InvoiceView extends VerticalLayout {
    private final InvoiceService invoiceService;

    public InvoiceView(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
        add(invoiceDtoGrid());
    }

    Grid<InvoiceDtoOut> invoiceDtoGrid() {
        Grid<InvoiceDtoOut> grid = new Grid<>(InvoiceDtoOut.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(InvoiceDtoOut::getId).setHeader("ID");
        grid.addColumn(InvoiceDtoOut::getSupplier).setHeader("Supplier");
        grid.addColumn(InvoiceDtoOut::getSubscriber).setHeader("Subscriber");
        grid.addColumn(InvoiceDtoOut::getInvoiceCreated).setHeader("Invoice created");
        grid.addColumn(InvoiceDtoOut::getDueDate).setHeader("Due date");
        grid.setItems(invoiceService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
