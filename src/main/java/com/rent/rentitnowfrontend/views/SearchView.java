package com.rent.rentitnowfrontend.views;

import com.rent.rentitnowfrontend.apis.backend.client.CartClient;
import com.rent.rentitnowfrontend.apis.backend.client.MovieClient;
import com.rent.rentitnowfrontend.apis.backend.domain.MovieDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@PageTitle("Movie Search")
@Route(value = "search")
public class SearchView extends VerticalLayout {

    private TopBarView topBarView = new TopBarView();
    private Grid<MovieDto> grid = new Grid<>(MovieDto.class);
    private FormLayout form = setMovieForm();
    private TextField filterText = new TextField();
    private HorizontalLayout hl = new HorizontalLayout();
    private Binder<MovieDto> binder = new Binder<>(MovieDto.class);
    private MovieClient movieClient = new MovieClient(new RestTemplate());
    private CartClient cartClient = new CartClient(new RestTemplate());

    public SearchView() {
        getElement().getStyle().set("background-color", "#f0f0f0");
        add(topBarView);
        add(new H1("List of movies"));
        filterText.setPlaceholder("Search by movie title...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        grid.setColumns("title", "director", "cast", "publicationDate", "price");

        hl.add(grid, form);
        hl.setSizeFull();
        grid.setSizeFull();

        add(filterText, hl);

        setAlignItems(Alignment.CENTER);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> setMovie(grid.asSingleSelect().getValue()));
    }

    public void refresh() {
        List<MovieDto> moviesList = movieClient.getAllMovies();
        grid.setItems(moviesList);
    }

    private FormLayout setMovieForm() {
        Button goToMoviePageButton = new Button("Go to movie page", event -> {
            MovieDto selectedMovie = grid.asSingleSelect().getValue();
            if (selectedMovie != null) {
                String movieId = selectedMovie.getMovieId().toString();
                String moviePageUrl = "movie/" + movieId;
                UI.getCurrent().navigate(moviePageUrl);
            }
        });

        Button addToCartButton = new Button("Add to cart", event -> {
            MovieDto selectedMovie = grid.asSingleSelect().getValue();
            if (selectedMovie != null) {
                cartClient.addMovieToCart(String.valueOf(1L), selectedMovie.getMovieId().toString());
                Notification.show("Movie added to cart!");
            }
        });

        FormLayout form = new FormLayout();
        form.add(goToMoviePageButton, addToCartButton);
        return form;
    }

    public void setMovie(MovieDto movieDto) {
        binder.setBean(movieDto);

        if (movieDto == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }
}
