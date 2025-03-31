package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.out.UserDtoOut;
import cz.patyk.invoicesystem_be.service.UserService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "user", layout = MainLayout.class)
public class UserView extends VerticalLayout {
    private final UserService userService;

    public UserView(UserService userService) {
        this.userService = userService;
        add(grid());
    }

    Grid<UserDtoOut> grid() {
        Grid<UserDtoOut> grid = new Grid<>(UserDtoOut.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(UserDtoOut::getId).setHeader("ID");
        grid.addColumn(UserDtoOut::getEmail).setHeader("Email");
        grid.addColumn(UserDtoOut::getFirstName).setHeader("First name");
        grid.addColumn(UserDtoOut::getLastName).setHeader("Last name");
        grid.setItems(userService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
