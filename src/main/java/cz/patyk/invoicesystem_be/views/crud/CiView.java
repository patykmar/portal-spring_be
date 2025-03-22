package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.out.CiDtoOut;
import cz.patyk.invoicesystem_be.service.CiService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "ci", layout = MainLayout.class)
public class CiView extends VerticalLayout {
    private final CiService ciService;

    public CiView(CiService ciService) {
        this.ciService = ciService;
        add(ciGrid());
    }

    Grid<CiDtoOut> ciGrid() {
        Grid<CiDtoOut> grid = new Grid<>(CiDtoOut.class, false);
        grid.addColumn(CiDtoOut::getId).setHeader("ID");
        grid.addColumn(CiDtoOut::getName).setHeader("Name");
        grid.setItems(ciService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
