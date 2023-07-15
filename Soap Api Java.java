package com.example.weatherapi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

//Simple Soap Weather Service API implementation
@WebService
public class WeatherService {

    @WebMethod
    public WeatherResponse getWeatherByCity(@WebParam(name = "CityName") String cityName) {
        // Logic to retrieve weather data for the specified city
        WeatherData weatherData = WeatherDataFetcher.fetchByCity(cityName);

        // Prepare and return the response
        WeatherResponse response = new WeatherResponse();
        response.setTemperature(weatherData.getTemperature());
        response.setHumidity(weatherData.getHumidity());
        response.setWindSpeed(weatherData.getWindSpeed());

        return response;
    }

    @WebMethod
    public WeatherResponse getWeatherByZip(@WebParam(name = "ZipCode") String zipCode) {
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


