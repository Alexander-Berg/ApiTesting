import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

public class WeatherServiceTest {
    private WeatherService weatherService;
    private WeatherDataFetcher weatherDataFetcher;

    @Before
    public void setUp() {
        weatherService = new WeatherService();
        weatherDataFetcher = mock(WeatherDataFetcher.class);
        weatherService.setWeatherDataFetcher(weatherDataFetcher);
    }

    @Test
    public void testGetWeatherByCity() {
        // Prepare test data
        String cityName = "New York";
        WeatherData weatherData = new WeatherData();
        weatherData.setTemperature("25°C");
        weatherData.setHumidity("60%");
        weatherData.setWindSpeed("10 mph");

        // Set up mock behavior
        when(weatherDataFetcher.fetchByCity(cityName)).thenReturn(weatherData);

        // Invoke the method being tested
        Response response = weatherService.getWeatherByCity(cityName);

        // Verify the response
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        WeatherResponse weatherResponse = (WeatherResponse) response.getEntity();
        assertEquals("25°C", weatherResponse.getTemperature());
        assertEquals("60%", weatherResponse.getHumidity());
        assertEquals("10 mph", weatherResponse.getWindSpeed());

        // Verify interaction with the mock
        verify(weatherDataFetcher).fetchByCity(cityName);
    }

    @Test
    public void testGetWeatherByZip() {
        // Prepare test data
        String zipCode = "12345";
        WeatherData weatherData = new WeatherData();
        weatherData.setTemperature("30°C");
        weatherData.setHumidity("50%");
        weatherData.setWindSpeed("5 mph");

        // Set up mock behavior
        when(weatherDataFetcher.fetchByZip(zipCode)).thenReturn(weatherData);

        // Invoke the method being tested
        Response response = weatherService.getWeatherByZip(zipCode);

        // Verify the response
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        WeatherResponse weatherResponse = (WeatherResponse) response.getEntity();
        assertEquals("30°C", weatherResponse.getTemperature());
        assertEquals("50%", weatherResponse.getHumidity());
        assertEquals("5 mph", weatherResponse.getWindSpeed());

        // Verify interaction with the mock
        verify(weatherDataFetcher).fetchByZip(zipCode);
    }
}
