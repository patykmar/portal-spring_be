package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.out.AddressDtoOut;
import cz.patyk.invoicesystem_be.service.AddressServices;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@Route(value = "address", layout = MainLayout.class)
public class AddressView extends VerticalLayout {
    private final AddressServices addressServices;

    @Autowired
    public AddressView(AddressServices addressServices) {
        this.addressServices = addressServices;
        add(addressGrid());
    }

    Grid<AddressDtoOut> addressGrid() {
        Grid<AddressDtoOut> grid = new Grid<>(AddressDtoOut.class, false);
        grid.addColumn(AddressDtoOut::getId).setHeader("ID");
        grid.addColumn(AddressDtoOut::getStreet).setHeader("Street");
        grid.addColumn(AddressDtoOut::getCity).setHeader("City");
        grid.addColumn(AddressDtoOut::getZipCode).setHeader("ZIP code");
        grid.addColumn(AddressDtoOut::getCountryDto).setHeader("Country");
        grid.setItems(addressServices.getAllAddresses(PageRequest.of(0, 10)));
        return grid;
    }
}
