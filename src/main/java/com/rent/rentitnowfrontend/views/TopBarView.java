package com.rent.rentitnowfrontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class TopBarView extends HorizontalLayout {

    private TextField searchField = new TextField();
    private HorizontalLayout buttonsLayout = new HorizontalLayout();
    private Button homeButton = new Button("Home", event -> {
        getUI().ifPresent(ui -> ui.navigate(HomeView.class));
    });
    private Button accountButton = new Button("User Account", event -> {
        getUI().ifPresent(ui -> ui.navigate(UserProfileView.class));
    });
    private Button contactButton = new Button("Contact Us", event -> {
        getUI().ifPresent(ui -> ui.navigate(ContactUsView.class));
    });
    private Button cartButton = new Button(new Icon(VaadinIcon.CART), event -> {
        getUI().ifPresent(ui -> ui.navigate(CartView.class));
    });

    public TopBarView() {
        searchField.setPlaceholder("Search");
        homeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        homeButton.getStyle().set("cursor", "pointer");
        accountButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        accountButton.getStyle().set("cursor", "pointer");
        contactButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        contactButton.getStyle().set("cursor", "pointer");
        cartButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cartButton.getStyle().set("cursor", "pointer");
        buttonsLayout.add(homeButton, accountButton, contactButton, cartButton);
        add(searchField, buttonsLayout);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setPadding(true);
    }
}
