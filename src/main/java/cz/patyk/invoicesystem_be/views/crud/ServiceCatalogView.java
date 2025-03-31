package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.out.ServiceCatalogDtoOut;
import cz.patyk.invoicesystem_be.service.ServiceCatalogService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "service-catalog", layout = MainLayout.class)
public class ServiceCatalogView extends VerticalLayout {
    private final ServiceCatalogService serviceCatalogService;

    public ServiceCatalogView(ServiceCatalogService serviceCatalogService) {
        this.serviceCatalogService = serviceCatalogService;
        add(serviceCatalogGrid());
    }

    Grid<ServiceCatalogDtoOut> serviceCatalogGrid() {
        Grid<ServiceCatalogDtoOut> grid = new Grid<>(ServiceCatalogDtoOut.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(ServiceCatalogDtoOut::getId).setHeader("ID");
        grid.addColumn(ServiceCatalogDtoOut::getName).setHeader("Name");
        grid.addColumn(ServiceCatalogDtoOut::getDescription).setHeader("Description");
        grid.addColumn(ServiceCatalogDtoOut::getPrice).setHeader("Price");
        grid.addColumn(ServiceCatalogDtoOut::getVatDtoOut).setHeader("Vat");
        grid.addColumn(ServiceCatalogDtoOut::getEstimateTimeDelivery).setHeader("Estimate time delivery");
        grid.addColumn(ServiceCatalogDtoOut::getEstimateTimeReaction).setHeader("Estimate time reaction");
        grid.addColumn(ServiceCatalogDtoOut::isDisable).setHeader("is disable?");
        grid.setItems(serviceCatalogService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
