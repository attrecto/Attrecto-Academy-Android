package example.android.attrecto.com.asynchnetworkcomplex;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import example.android.attrecto.com.asynchnetworkcomplex.openweathermap.CurrentWeather;
import example.android.attrecto.com.asynchnetworkcomplex.openweathermap.OpenWeatherMap;

/**
 * Created by user on 2018. 04. 21..
 */

public class MainActivityPresenter {
    public CurrentWeatherViewContent getCurrentWeather(long cityId) throws IOException {
        CurrentWeather currentWeather = OpenWeatherMap.getInstance().getCurrentWeather(cityId);
        CurrentWeatherViewContent currentWeatherViewContent = new CurrentWeatherViewContent();

        currentWeatherViewContent.cityName = currentWeather.name;
        currentWeatherViewContent.weatherDescription = currentWeather.weather.get(0).description;
        currentWeatherViewContent.windSpeed = currentWeather.wind.speed + "km/h";
        currentWeatherViewContent.imageUrl = OpenWeatherMap.getInstance().getWeatherIconUrl(currentWeather.weather.get(0).icon);

        return currentWeatherViewContent;
    }
}
