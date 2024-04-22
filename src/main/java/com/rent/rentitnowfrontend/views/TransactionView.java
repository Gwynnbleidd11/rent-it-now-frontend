package com.rent.rentitnowfrontend.views;

import com.rent.rentitnowfrontend.apis.backend.client.CartClient;
import com.rent.rentitnowfrontend.apis.backend.client.MovieClient;
import com.rent.rentitnowfrontend.apis.backend.domain.CartDto;
import com.rent.rentitnowfrontend.apis.backend.domain.MovieDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;

@PageTitle("Transaction")
@Route("transaction")
public class TransactionView extends VerticalLayout {

    private TopBarView topBarView = new TopBarView();
    private CartClient cartClient = new CartClient(new RestTemplate());
    private MovieClient movieClient = new MovieClient(new RestTemplate());

    public TransactionView() {
        getElement().getStyle().set("background-color", "#f0f0f0");
        add(topBarView);
        VerticalLayout movieLayout = new VerticalLayout();
        Div choosePayment = new Div("Choose your payment form");
        ListBox<String> paymentForm = new ListBox<>();
        paymentForm.setItems("Blik", "Credit/Debit card", "Pay-Pal");

        CartDto cartDto = cartClient.getCart(2L);
        BigDecimal sum = new BigDecimal(BigInteger.ZERO);
        for (Long movieId: cartDto.getMovieIds()) {
            MovieDto movieDto = movieClient.getMovie(movieId.toString());
            addMovie(movieLayout, movieDto);
            sum = sum.add(movieDto.getPrice());
        }
        H4 paymentAmount = new H4("Amount to pay: " + sum);
        Button payButton = new Button("Click to pay");
        payButton.getStyle().set("cursor", "pointer");
        payButton.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate(MovieRentedView.class));
        });
        add(movieLayout, choosePayment, paymentForm, paymentAmount, payButton);

        movieLayout.setAlignItems(Alignment.CENTER);

        setAlignItems(Alignment.CENTER);
        setSizeFull();
    }

    private void addMovie(VerticalLayout movieList, MovieDto movieDto) {
        HorizontalLayout movieLayout = new HorizontalLayout();
        movieLayout.add(new H4(movieDto.getTitle()));
        movieLayout.add(new H4(movieDto.getPrice().toString()));
        movieList.add(movieLayout);
    }
}
