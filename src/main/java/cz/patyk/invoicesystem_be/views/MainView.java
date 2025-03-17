package cz.patyk.invoicesystem_be.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

@Route(value = "", layout = MainLayout.class)
@Component
public class MainView extends VerticalLayout {

    public MainView() {
        // Add a simple button with a notification
//        Button button = new Button("Click Me",
//                event -> Notification.show("Hello, Vaadin!"));
//        add(button);
    }

}
