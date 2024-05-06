package com.rent.rentitnowfrontend.views;

import com.rent.rentitnowfrontend.apis.rapidapi.client.WeatherClient;
import com.rent.rentitnowfrontend.apis.rapidapi.domain.weather.CoordsDto;
import com.rent.rentitnowfrontend.apis.rapidapi.domain.weather.WeatherDataResponseDto;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherView extends VerticalLayout {

    private ScheduledExecutorService executorService;
    private WeatherClient weatherClient = new WeatherClient(new RestTemplate());

    public WeatherView() {
        getElement().getStyle().set("background-color", "lightblue");
        VerticalLayout vl = new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();
        Image image = new Image();
        Span response = new Span();
        Span description = new Span();
        Span temperature = new Span();
        Span humidity = new Span();
        vl.add(response, description, temperature, humidity);
        hl.add(image, vl);
        add(hl);
        updateWeatherData(image, response, description, temperature, humidity);

        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            UI.getCurrent().access(() -> {
                updateWeatherData(image, response, description, temperature, humidity);
            });
        }, 0, 30, TimeUnit.MINUTES);

        VerticalLayout vl2 = new VerticalLayout();

        setAlignItems(Alignment.CENTER);
    }

    private void updateWeatherData(Image image, Span response, Span description, Span temperature, Span humidity) {
        List<CoordsDto> coordsResponse = weatherClient.getCoords("Kraków");
        Optional<CoordsDto> cityCoords = coordsResponse.stream()
                .filter(c -> c.getCountry().equals("PL"))
                .findFirst();
        String desc = "";
        double temp = 0.0;
        int hum;
        if (cityCoords.isPresent()) {
            WeatherDataResponseDto weatherResponse = weatherClient.getWeather(cityCoords.get().getLat(), cityCoords.get().getLon());
            desc = weatherResponse.getWeather()[0].getMain();
            temp = weatherResponse.getMain().getTemp();
            hum = weatherResponse.getMain().getHumidity();
            description.setText("Today's weather: " + desc);
            temperature.setText("Current temperature: " + temp + "°C");
            humidity.setText("Today's humidity: " + hum + "%");
            image.setSrc(getWeatherPictureUrl(desc));
        }
        if (desc.equals("Clear") || desc.equals("Clouds") && temp >= 20.00) {
            response.setText("Today it's a beautiful day, consider going out and watching movie in the evening!");
        } else {
            response.setText("It's a perfect day for some movie enjoyment! Have fun");
        }
    }

    private String getWeatherPictureUrl(String weather) {
        switch (weather) {
            case "Thunderstorm" -> {
                return "https://openweathermap.org/img/wn/11d@2x.png";
            }
            case "Drizzle", "Rain" -> {
                return "https://openweathermap.org/img/wn/09d@2x.png";
            }
            case "Snow" -> {
                return "https://openweathermap.org/img/wn/13d@2x.png";
            }
            case "Clear" -> {
                return "https://openweathermap.org/img/wn/01d@2x.png";
            }
            case "Clouds" -> {
                return "https://openweathermap.org/img/wn/04d@2x.png";
            }
            default ->
                throw new IllegalArgumentException("Unexpected weather value: " + weather);
        }
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        executorService.shutdown();
        super.onDetach(detachEvent);
    }
}
