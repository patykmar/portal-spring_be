package cz.patyk.invoicesystem_be.views.crud;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import cz.patyk.invoicesystem_be.dto.out.PaymentTypeDtoOut;
import cz.patyk.invoicesystem_be.service.PaymentTypeService;
import cz.patyk.invoicesystem_be.views.MainLayout;
import org.springframework.data.domain.PageRequest;

@Route(value = "paymentType", layout = MainLayout.class)
public class PaymentTypeView extends VerticalLayout {
    private final PaymentTypeService paymentTypeService;

    public PaymentTypeView(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
        add(paymentTypeGrid());
    }

    Grid<PaymentTypeDtoOut> paymentTypeGrid() {
        Grid<PaymentTypeDtoOut> grid = new Grid<>(PaymentTypeDtoOut.class, false);
        grid.addColumn(PaymentTypeDtoOut::getId).setHeader("ID");
        grid.addColumn(PaymentTypeDtoOut::getName).setHeader("Name");
        grid.addColumn(PaymentTypeDtoOut::isDefault).setHeader("is default?");
        grid.setItems(paymentTypeService.getAll(PageRequest.of(0, 10)));
        return grid;
    }
}
