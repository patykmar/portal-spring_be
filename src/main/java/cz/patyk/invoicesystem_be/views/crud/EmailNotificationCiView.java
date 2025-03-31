package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.out.EmailNotificationCiDtoOut;
import cz.patyk.invoicesystem_be.service.EmailNotificationCiService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "email-notification-ci", layout = MainLayout.class)
public class EmailNotificationCiView extends VerticalLayout {
    private final EmailNotificationCiService emailNotificationCiService;

    public EmailNotificationCiView(EmailNotificationCiService emailNotificationCiService) {
        this.emailNotificationCiService = emailNotificationCiService;
        add(emailNotificationCiGrid());
    }

    Grid<EmailNotificationCiDtoOut> emailNotificationCiGrid() {
        Grid<EmailNotificationCiDtoOut> grid = new Grid<>(EmailNotificationCiDtoOut.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(EmailNotificationCiDtoOut::getId).setHeader("ID");
        grid.addColumn(EmailNotificationCiDtoOut::getEmailAddress).setHeader("Email address");
        grid.addColumn(EmailNotificationCiDtoOut::getCi).setHeader("CI");
        grid.setItems(emailNotificationCiService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
