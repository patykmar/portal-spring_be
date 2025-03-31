package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.out.QueueUserDtoOut;
import cz.patyk.invoicesystem_be.service.QueueUserService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "queue-user", layout = MainLayout.class)
public class QueueUserView extends VerticalLayout {
    private final QueueUserService queueUserService;

    public QueueUserView(QueueUserService queueUserService) {
        this.queueUserService = queueUserService;
        add(queueUserGrid());
    }

    Grid<QueueUserDtoOut> queueUserGrid() {
        Grid<QueueUserDtoOut> grid = new Grid<>(QueueUserDtoOut.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(QueueUserDtoOut::getId).setHeader("ID");
        grid.addColumn(QueueUserDtoOut::getUserDto).setHeader("User");
        grid.addColumn(QueueUserDtoOut::getQueueDto).setHeader("Queue");
        grid.setItems(queueUserService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
