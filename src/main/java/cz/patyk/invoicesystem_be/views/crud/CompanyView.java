package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.out.CompanyDtoOut;
import cz.patyk.invoicesystem_be.service.CompanyService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "company", layout = MainLayout.class)
public class CompanyView extends VerticalLayout {
    private final CompanyService companyService;

    public CompanyView(CompanyService companyService) {
        this.companyService = companyService;
        add(companyDtoOutGrid());
    }

    Grid<CompanyDtoOut> companyDtoOutGrid() {
        Grid<CompanyDtoOut> grid = new Grid<>(CompanyDtoOut.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(CompanyDtoOut::getId).setHeader("ID");
        grid.addColumn(CompanyDtoOut::getName).setHeader("Name");
        grid.addColumn(CompanyDtoOut::getDescription).setHeader("Description");
        grid.addColumn(CompanyDtoOut::getCompanyId).setHeader("Company ID");
        grid.setItems(companyService.getAll(PageRequest.of(0, 10)));
        return grid;
    }

}
