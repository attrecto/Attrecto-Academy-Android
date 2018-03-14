package example.android.attrecto.com.asynchnetworkcomplex.openweathermap;

import java.util.List;

public class CurrentWeather {
    public class Coord {
        public double lat;
        public double lon;
    }

    public class WeatherItem {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public class Main {
        public double temp;
        public double pressure;
        public double humidity;
        public double temp_min;
        public double temp_max;

    }

    public class Wind {
        public double speed;
        public double deg;
    }

    public class Clouds {
        public double all;
    }

    public class Sys {
        public int type;
        public long id;
        public double message;
        public String country;
        public long sunrise;
        public long sunset;
    }

    public Coord coord;
    public List<WeatherItem> weather;
    public Main main;
    public long visibility;
    public Wind wind;
    public Clouds clouds;
    public long dt;
    public Sys sys;
    public long id;
    public String name;
    public long cod;
}
