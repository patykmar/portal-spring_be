package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.QueueDto;
import cz.patyk.invoicesystem_be.service.QueueServices;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "queue", layout = MainLayout.class)
public class QueueView extends VerticalLayout {
    private final QueueServices queueServices;

    public QueueView(QueueServices queueServices) {
        this.queueServices = queueServices;
        add(queueGrid());
    }

    Grid<QueueDto> queueGrid() {
        Grid<QueueDto> grid = new Grid<>(QueueDto.class, false);
        grid.addColumn(QueueDto::getId).setHeader("ID");
        grid.addColumn(QueueDto::getName).setHeader("Name");
        grid.setItems(queueServices.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
