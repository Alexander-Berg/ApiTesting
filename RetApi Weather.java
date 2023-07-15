package com.example.weatherapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/weather")
public class WeatherService {

    @GET
    @Path("/city/{cityName}")
    @Produces(MediaType.APPLICATION_JSON)
    public WeatherResponse getWeatherByCity(@PathParam("cityName") String cityName) {
        // Logic to retrieve weather data for the specified city
        WeatherData weatherData = WeatherDataFetcher.fetchByCity(cityName);

        // Prepare and return the response
        WeatherResponse response = new WeatherResponse();
        response.setTemperature(weatherData.getTemperature());
        response.setHumidity(weatherData.getHumidity());
        response.setWindSpeed(weatherData.getWindSpeed());

        return response;
    }

    @GET
    @Path("/zip/{zipCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public WeatherResponse getWeatherByZip(@PathParam("zipCode") String zipCode) {
        // Logic to retrieve weather data for the specified zip code
        WeatherData weatherData = WeatherDataFetcher.fetchByZip(zipCode);

        // Prepare and return the response
        WeatherResponse response = new WeatherResponse();
        response.setTemperature(weatherData.getTemperature());
        response.setHumidity(weatherData.getHumidity());
        response.setWindSpeed(weatherData.getWindSpeed());

        return response;
    }
}
