package com.rent.rentitnowfrontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("home")
public class HomeView extends VerticalLayout {

    private TopBarView topBarView = new TopBarView();

    public HomeView() {
        getElement().getStyle().set("background-color", "#f0f0f0");
        add(topBarView);
        Button searchButton = new Button("Click to search for movies", e -> {
            getUI().ifPresent(ui -> ui.navigate(SearchView.class));
        });
        searchButton.getStyle().set("cursor", "pointer");
        Image movie1 = addMovieImage("https://fwcdn.pl/fpo/94/76/469476/8030930.6.jpg", "Movie 1");
        Image movie2 = addMovieImage("https://fwcdn.pl/fpo/07/59/759/8037811.6.jpg", "Movie 2");
        Image movie3 = addMovieImage("https://fwcdn.pl/fpo/10/65/1065/7912491.6.jpg", "Movie 3");
        HorizontalLayout moviesLayout = new HorizontalLayout(movie1, movie2, movie3);
        moviesLayout.setSpacing(true);
        add(new H2("Welcome to Rent-It-Now!"), searchButton, new H2("Top 3 movies"), moviesLayout);
        setAlignItems(Alignment.CENTER);
        setSpacing(true);
        setSizeFull();
    }

    private Image addMovieImage(String url, String title) {
        Image movie = new Image(url, title);
        movie.getStyle().set("cursor", "pointer");
        movie.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate(MovieView.class));
        });
        return movie;
    }
}

