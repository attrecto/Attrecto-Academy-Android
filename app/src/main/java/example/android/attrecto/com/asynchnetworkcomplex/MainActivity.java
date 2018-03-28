package example.android.attrecto.com.asynchnetworkcomplex;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import example.android.attrecto.com.asynchnetworkcomplex.openweathermap.CurrentWeather;
import example.android.attrecto.com.asynchnetworkcomplex.openweathermap.OpenWeatherMap;

public class MainActivity extends AppCompatActivity {
    private static final int CITY_ID_GYOR_MOSON_SOPRON_MEGYE = 3051977;

    private AsyncTask<Void, Void, CurrentWeather> loadAsyncTask;

    private View contentView;
    private View loadingView;

    private TextView errorTextView;

    private TextView cityNameTextView;
    private TextView weatherDescriptionTextView;
    private TextView windSpeedTextView;

    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWeatherInfo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelLoad();
    }

    private void cancelLoad() {
        if (loadAsyncTask != null) {
            loadAsyncTask.cancel(true);
        }
    }

    private void initUi() {
        setContentView(R.layout.activity_main);

        contentView = findViewById(R.id.contentContainer);
        loadingView = findViewById(R.id.progressBar);
        errorTextView = findViewById(R.id.errorTextView);

        cityNameTextView = findViewById(R.id.cityNameTextView);
        weatherDescriptionTextView = findViewById(R.id.weatherDescriptionTextView);
        windSpeedTextView = findViewById(R.id.windSpeedTextView);
        imageView = findViewById(R.id.imageView);
    }

    @SuppressLint("StaticFieldLeak")
    private void loadWeatherInfo() {
        loadAsyncTask = new AsyncTask<Void, Void, CurrentWeather>() {
            private Exception exception;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                displayLoading();
            }

            @Override
            protected CurrentWeather doInBackground(Void... voids) {
                try {
                    return OpenWeatherMap.getInstance().getCurrentWeather(CITY_ID_GYOR_MOSON_SOPRON_MEGYE);
                } catch (IOException e) {
                    exception = e;
                    return null;
                }
            }

            @Override
            protected void onPostExecute(CurrentWeather currentWeather) {
                super.onPostExecute(currentWeather);
                loadAsyncTask = null;
                if (!isFinishing()) {
                    if (currentWeather != null) {
                        displayContent(currentWeather);
                    } else {
                        displayError(exception);
                    }
                }
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                loadAsyncTask = null;
            }
        }.execute();
    }

    private void displayLoading() {
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
        errorTextView.setVisibility(View.GONE);
    }

    private void displayContent(CurrentWeather currentWeather) {
        contentView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        errorTextView.setVisibility(View.GONE);

        cityNameTextView.setText(currentWeather.name);
        weatherDescriptionTextView.setText(currentWeather.weather.get(0).description);
        windSpeedTextView.setText(currentWeather.wind.speed + "km/h");

        Picasso.get().load(OpenWeatherMap.getInstance().getWeatherIconUrl(currentWeather.weather.get(0).icon))
                .into(imageView);
    }

    private void displayError(Exception exception) {
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);

        errorTextView.setText(exception.getLocalizedMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadAsyncTask = null;
    }
}