package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.CountryDto;
import cz.patyk.invoicesystem_be.service.CountryService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Route(value = "country", layout = MainLayout.class)
public class CountryView extends VerticalLayout {

    @Autowired
    public CountryView(CountryService countryService) {

        PageRequest pageRequest = PageRequest.of(0, 10);

        Grid<CountryDto> grid = new Grid<>(CountryDto.class, false);
        grid.addColumn(CountryDto::getId).setHeader("ID");
        grid.addColumn(CountryDto::getName).setHeader("Country name");
        grid.addColumn(CountryDto::getIso3166alpha3).setHeader("Iso3166alpha3");

        List<CountryDto> people = countryService.getAllCountries(pageRequest);
        grid.setItems(people);

        Button button = new Button("Click Me", event -> Notification.show("Add"));
        add(grid);
        add(button);
    }
}
