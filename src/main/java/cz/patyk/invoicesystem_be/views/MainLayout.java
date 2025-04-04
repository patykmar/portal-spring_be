package cz.patyk.invoicesystem_be.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import cz.patyk.invoicesystem_be.views.crud.AddressView;
import cz.patyk.invoicesystem_be.views.crud.CiView;
import cz.patyk.invoicesystem_be.views.crud.CompanyView;
import cz.patyk.invoicesystem_be.views.crud.CountryView;
import cz.patyk.invoicesystem_be.views.crud.EmailNotificationCiView;
import cz.patyk.invoicesystem_be.views.crud.GeneralStateView;
import cz.patyk.invoicesystem_be.views.crud.InfluencingTicketView;
import cz.patyk.invoicesystem_be.views.crud.InvoiceView;
import cz.patyk.invoicesystem_be.views.crud.PaymentTypeView;
import cz.patyk.invoicesystem_be.views.crud.QueueUserView;
import cz.patyk.invoicesystem_be.views.crud.QueueView;
import cz.patyk.invoicesystem_be.views.crud.ServiceCatalogView;
import cz.patyk.invoicesystem_be.views.crud.SlaView;
import cz.patyk.invoicesystem_be.views.crud.TariffView;
import cz.patyk.invoicesystem_be.views.crud.TicketTypeView;
import cz.patyk.invoicesystem_be.views.crud.UserView;
import cz.patyk.invoicesystem_be.views.crud.VatView;
import cz.patyk.invoicesystem_be.views.crud.WorkInventoryView;

public class MainLayout extends AppLayout {
    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addNavbarContent();
        addDrawerContent();
    }

    private void addNavbarContent() {
        var toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");
        toggle.setTooltipText("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE, LumoUtility.Flex.GROW);

        var header = new Header(toggle, viewTitle);
        header.addClassNames(LumoUtility.AlignItems.CENTER, LumoUtility.Display.FLEX,
                LumoUtility.Padding.End.MEDIUM, LumoUtility.Width.FULL);

        addToNavbar(false, header);
    }

    private void addDrawerContent() {
        var appName = new Span("Invoices System");
        appName.addClassNames(LumoUtility.AlignItems.CENTER, LumoUtility.Display.FLEX,
                LumoUtility.FontSize.LARGE, LumoUtility.FontWeight.SEMIBOLD,
                LumoUtility.Height.XLARGE, LumoUtility.Padding.Horizontal.MEDIUM);

        addToDrawer(appName, new Scroller(createSideNav()));
    }

    private SideNav createSideNav() {
        SideNav nav = new SideNav();

        nav.setLabel("Main");
        nav.addItem(new SideNavItem("Lobby", MainView.class, VaadinIcon.BUILDING.create()));
        nav.addItem(new SideNavItem("Invoice", InvoiceView.class, VaadinIcon.INVOICE.create()));
        nav.addItem(new SideNavItem("CI", CiView.class, VaadinIcon.COGS.create()));

        SideNavItem sideNavAddressesNav = new SideNavItem("Addresses");
        sideNavAddressesNav.setExpanded(true);
        sideNavAddressesNav.addItem(new SideNavItem("Address", AddressView.class, VaadinIcon.MAP_MARKER.create()));
        sideNavAddressesNav.addItem(new SideNavItem("Country", CountryView.class, VaadinIcon.GLOBE.create()));

        SideNavItem sideNavPaymentCatalog = new SideNavItem("Payment catalog");
        sideNavPaymentCatalog.setExpanded(true);
        sideNavPaymentCatalog.addItem(new SideNavItem("Company", CompanyView.class, VaadinIcon.BUILDING.create()));
        sideNavPaymentCatalog.addItem(new SideNavItem("Payment type", PaymentTypeView.class, VaadinIcon.WALLET.create()));
        sideNavPaymentCatalog.addItem(new SideNavItem("Vat", VatView.class, VaadinIcon.CASH.create()));
        sideNavPaymentCatalog.addItem(new SideNavItem("Work inventory", WorkInventoryView.class, VaadinIcon.WORKPLACE.create()));

        SideNavItem sideNavItem = new SideNavItem("Ticket catalog");
        sideNavItem.setExpanded(true);
        sideNavItem.addItem(new SideNavItem("Email notification ci", EmailNotificationCiView.class, VaadinIcon.START_COG.create()));
        sideNavItem.addItem(new SideNavItem("General state", GeneralStateView.class, VaadinIcon.CONTROLLER.create()));
        sideNavItem.addItem(new SideNavItem("Influencing Ticket", InfluencingTicketView.class, VaadinIcon.ADJUST.create()));
        sideNavItem.addItem(new SideNavItem("Service catalog", ServiceCatalogView.class, VaadinIcon.LINES_LIST.create()));
        sideNavItem.addItem(new SideNavItem("SLA", SlaView.class, VaadinIcon.LIST_SELECT.create()));
        sideNavItem.addItem(new SideNavItem("Tariff", TariffView.class, VaadinIcon.BAR_CHART.create()));
        sideNavItem.addItem(new SideNavItem("Ticket type", TicketTypeView.class, VaadinIcon.CHEVRON_CIRCLE_UP_O.create()));
        sideNavItem.addItem(new SideNavItem("Users", UserView.class, VaadinIcon.USERS.create()));
        sideNavItem.addItem(new SideNavItem("User queue", QueueUserView.class, VaadinIcon.USER_CARD.create()));
        sideNavItem.addItem(new SideNavItem("Queue", QueueView.class, VaadinIcon.EXCHANGE.create()));

        nav.addItem(sideNavAddressesNav);
        nav.addItem(sideNavPaymentCatalog);
        nav.addItem(sideNavItem);

        return nav;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        if (getContent() == null) {
            return "";
        } else if (getContent() instanceof HasDynamicTitle titleHolder) {
            return titleHolder.getPageTitle();
        } else {
            var title = getContent().getClass().getAnnotation(PageTitle.class);
            return title == null ? "" : title.value();
        }
    }
}
