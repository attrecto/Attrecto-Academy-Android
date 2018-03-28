package example.android.attrecto.com.asynchnetworkcomplex.openweathermap;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OpenWeatherMap {
    private static final String API_KEY= "9c8b7f07f97069b3a21fb8fefd85ccb5";
    private static final String API_KEY_PRAMETER_NAME= "APPID";
    private static final String CITY_ID_PRAMETER_NAME= "id";

    private static final String WEATHER_BY_CITY_ID_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String ICON_URL = "http://openweathermap.org/img/w/%s.png";

    private static OpenWeatherMap instance;

    private OkHttpClient client;

    public static synchronized OpenWeatherMap getInstance(){
        if(instance == null){
            instance = new OpenWeatherMap();
        }
        return instance;
    }

    private OpenWeatherMap(){
        client = new OkHttpClient();
    }

    public CurrentWeather getCurrentWeather(long cityId) throws IOException {

        HttpUrl url = HttpUrl.parse(WEATHER_BY_CITY_ID_URL);
        url = url.newBuilder()
                .addEncodedQueryParameter(CITY_ID_PRAMETER_NAME, String.valueOf(cityId))
                .addEncodedQueryParameter(API_KEY_PRAMETER_NAME, API_KEY).build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String jsonString = response.body().string();
        response.close();

        return new Gson().fromJson(jsonString, CurrentWeather.class);
    }

    public String getWeatherIconUrl(String icon){
        return String.format(ICON_URL, icon);
    }
}
