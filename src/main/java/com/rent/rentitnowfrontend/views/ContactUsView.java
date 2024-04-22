package com.rent.rentitnowfrontend.views;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Contact Us")
@Route(value = "contact")
public class ContactUsView extends VerticalLayout {

    private TopBarView topBarView = new TopBarView();

    public ContactUsView() {
        getElement().getStyle().set("background-color", "#f0f0f0");
        add(topBarView);
        add(    new H3("Thank you for using our platform."),
                new H3("If you would like to contact us please use details below:"),
                new H4("e-mail: rent-it-now@gmail.com"),
                new H4("Phone number: 123-456-789"));
        setAlignItems(Alignment.CENTER);
        setSizeFull();
    }
}
