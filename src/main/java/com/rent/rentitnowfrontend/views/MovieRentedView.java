package com.rent.rentitnowfrontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "rented_movie")
public class MovieRentedView extends VerticalLayout {

    TopBarView topBarView = new TopBarView();

    public MovieRentedView() {
        getElement().getStyle().set("background-color", "#f0f0f0");
        add(topBarView);
        Button homePageButton = new Button("Go back to Home Page", event -> {
            getUI().ifPresent(ui -> ui.navigate(HomeView.class));
        });
        homePageButton.getStyle().set("cursor", "pointer");
        Button userPageButton = new Button("User Account", event -> {
            getUI().ifPresent(ui -> ui.navigate(UserProfileView.class));
        });
        userPageButton.getStyle().set("cursor", "pointer");
        add(new Div("Thank you for renting from us!"), homePageButton,
                new Div("If you would like to check details of your order, please go to your User Page"), userPageButton);

        setAlignItems(Alignment.CENTER);
        setSizeFull();
    }
}
