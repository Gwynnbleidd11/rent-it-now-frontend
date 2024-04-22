package com.rent.rentitnowfrontend.views;

import com.rent.rentitnowfrontend.apis.backend.client.RentClient;
import com.rent.rentitnowfrontend.apis.backend.client.UserClient;
import com.rent.rentitnowfrontend.apis.backend.domain.RentDto;
import com.rent.rentitnowfrontend.apis.backend.domain.UserDto;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@PageTitle("User Profile")
@Route(value = "user")
public class UserProfileView extends VerticalLayout {

    private TopBarView topBarView = new TopBarView();
    private UserClient client = new UserClient(new RestTemplate());
    private Grid<RentDto> rentsGrid = new Grid<>(RentDto.class);
    private Grid<RentDto> currentRentsGrid = new Grid<>(RentDto.class);
    private RentClient rentClient = new RentClient(new RestTemplate());

    public UserProfileView() {
        getElement().getStyle().set("background-color", "#f0f0f0");
        add(topBarView);
        HorizontalLayout hl = new HorizontalLayout();
        VerticalLayout userDetailsLayout = setDetailsLayout(2L);
        userDetailsLayout.setAlignItems(Alignment.CENTER);
        VerticalLayout rentsLayout = setUserRents();
        rentsLayout.setAlignItems(Alignment.CENTER);
        hl.add(userDetailsLayout, rentsLayout);
        hl.setSizeFull();
        add(hl);
        setAlignItems(Alignment.CENTER);
        setSizeFull();
        rentsLayout.setSizeFull();

        refresh();
    }

    private VerticalLayout setDetailsLayout(final Long userId) {
        UserDto user = client.getUser(userId);
        VerticalLayout userDetailsLayout = new VerticalLayout();
        H3 yourDetails = new H3("Your details:");
        List<Component> detailsList = List.of(
                new Div("First Name: " + user.getFirstname()),
                new Div("Last Name: " + user.getLastname()),
                new Div("email: " + user.getEmail()),
                new Div("Password"),
                new Div("Phone Number: " + user.getPhoneNumber()),
                new Div("Date of birth: " + user.getBirthDate()));
        userDetailsLayout.add(yourDetails);
        for (Component detail : detailsList) {
            HorizontalLayout hl = new HorizontalLayout();
            Button editButton = new Button("Edit");
            editButton.getStyle().set("cursor", "pointer");
            hl.add(detail, editButton);
            hl.setAlignItems(Alignment.BASELINE);
            userDetailsLayout.add(hl);
        }
        return userDetailsLayout;
    }
    private VerticalLayout setUserRents() {
        VerticalLayout transactionLayout = new VerticalLayout();
        currentRentsGrid.setColumns("rentId", "movie", "cost", "rentDate", "returnDate");
        rentsGrid.setColumns("rentId", "movie", "cost", "rentDate", "returnDate");
        transactionLayout.add(new H3("Currently rented movies:"), currentRentsGrid,
                new H3("History of your rents:"), rentsGrid);
        currentRentsGrid.setSizeFull();
        rentsGrid.setSizeFull();
        return transactionLayout;
    }

    private void refresh() {
        List<RentDto> rentsList = rentClient.getUserRents(2L );
        rentsGrid.setItems(rentsList);
        List<RentDto> currentRents = rentClient.getCurrentRents(2L);
        currentRentsGrid.setItems(currentRents);
    }
}
