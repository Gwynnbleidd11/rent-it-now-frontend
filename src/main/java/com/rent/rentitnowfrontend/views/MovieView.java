package com.rent.rentitnowfrontend.views;

import com.rent.rentitnowfrontend.apis.backend.client.CartClient;
import com.rent.rentitnowfrontend.apis.backend.client.MovieClient;
import com.rent.rentitnowfrontend.apis.backend.domain.MovieDto;
import com.rent.rentitnowfrontend.apis.rapidapi.client.IMDbClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.web.client.RestTemplate;

@Route(value = "movie")
@RouteAlias("movie/:movieId")
public class MovieView extends VerticalLayout implements BeforeEnterObserver {

    private String movieId;
    private MovieClient movieClient = new MovieClient(new RestTemplate());
    private IMDbClient imdbClient = new IMDbClient(new RestTemplate());
    private CartClient cartClient = new CartClient(new RestTemplate());

    public MovieView() {
        getElement().getStyle().set("background-color", "#f0f0f0");
        TopBarView topBarView = new TopBarView();
        add(topBarView);

        setAlignItems(Alignment.CENTER);
        setSizeFull();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        movieId = event.getRouteParameters().get("movieId").orElse(null);
        MovieDto movie = movieClient.getMovie(movieId);
        populateView(movie);

    }

    private void populateView(MovieDto movie) {
//        Image posterImage = new Image(imdbClient.getIMDbMovieImage(movie.getImdbMovieId()), "Movie Poster");
        Image posterImage = new Image("temporary disabled", "Movie Poster");
        posterImage.setHeight("500px");
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.addClickListener(event -> {
            cartClient.addMovieToCart(String.valueOf(2L), movie.getMovieId().toString());
            Notification.show("Movie added to cart!");
        });

        VerticalLayout detailsLayout = new VerticalLayout();
        detailsLayout.add(
                new Div("Title: " + movie.getTitle()),
                new Div("Director: " + movie.getDirector()),
                new Div("Cast: " + movie.getCast()),
                new Div("Publication Date: " + movie.getPublicationDate()),
//                new Div("IMDb rating : " + imdbClient.getIMDbRating(movie.getImdbMovieId())), //implement getting IMDb rating
//                new Div("Metacritic score: " + imdbClient.getMatacriticScore(movie.getImdbMovieId())), //implement getting metacritic score
                new Div("Price: " + movie.getPrice()),
                addToCartButton
        );

        HorizontalLayout movieLayout = new HorizontalLayout();
        movieLayout.add(posterImage, detailsLayout);
        movieLayout.setWidthFull();

//        Video video = new Video("videos/vaadin-channel.mp4");
//        video.setWidth("480px");
//
        add(movieLayout);
        setSizeFull();
    }
}
