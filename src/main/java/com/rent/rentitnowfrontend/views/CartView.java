package com.rent.rentitnowfrontend.views;

import com.rent.rentitnowfrontend.apis.backend.client.CartClient;
import com.rent.rentitnowfrontend.apis.backend.client.MovieClient;
import com.rent.rentitnowfrontend.apis.backend.domain.CartDto;
import com.rent.rentitnowfrontend.apis.backend.domain.MovieDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;

@PageTitle("Cart")
@Route("cart")
public class CartView extends VerticalLayout {

    private TopBarView topBarView = new TopBarView();
    private CartClient cartClient = new CartClient(new RestTemplate());
    private MovieClient movieClient = new MovieClient(new RestTemplate());

    public CartView() {
        getElement().getStyle().set("background-color", "#f0f0f0");
        add(topBarView);
        VerticalLayout movieList = new VerticalLayout();
        movieList.setAlignItems(Alignment.CENTER);
        CartDto cartDto = cartClient.getCart(1L);
        for (Long movieId: cartDto.getMovieIds()) {
            MovieDto movieDto = movieClient.getMovie(movieId.toString());
            addMovie(movieList, cartDto, movieDto);
        }
        Button payButton = new Button("Pay to rent");
        payButton.addClickListener(event -> {
           cartClient.createTransaction(cartDto.getCartId().toString());
        });
        payButton.getStyle().set("cursor", "pointer");
        payButton.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate(TransactionView.class));
        });
        add(movieList, payButton);

        setAlignItems(Alignment.CENTER);
        setSizeFull();
    }

    private void addMovie(VerticalLayout movieList, CartDto cartDto, MovieDto movieDto) {
        HorizontalLayout movieLayout = new HorizontalLayout();
        Button removeButton = new Button("Remove from cart");
        removeButton.getStyle().set("cursor", "pointer");
        removeButton.addClickListener(event -> {
            cartClient.removeMovieFromCart(cartDto.getCartId().toString(),
                    movieDto.getMovieId().toString());
            Notification.show("Movie removed from cart!");
        });
        movieLayout.add(new Div(movieDto.getTitle()), removeButton);
        movieLayout.setAlignItems(Alignment.BASELINE);
        movieList.add(movieLayout);
    }
}