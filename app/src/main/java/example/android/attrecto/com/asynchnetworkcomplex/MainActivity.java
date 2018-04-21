package example.android.attrecto.com.asynchnetworkcomplex;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import example.android.attrecto.com.asynchnetworkcomplex.databinding.ActivityMainBinding;
import example.android.attrecto.com.asynchnetworkcomplex.openweathermap.CurrentWeather;
import example.android.attrecto.com.asynchnetworkcomplex.openweathermap.OpenWeatherMap;

public class MainActivity extends AppCompatActivity {
    private static final int CITY_ID_GYOR_MOSON_SOPRON_MEGYE = 3051977;

    private AsyncTask<Void, Void, CurrentWeatherViewContent> loadAsyncTask;

    private View contentView;
    private View loadingView;

    private TextView errorTextView;

    ActivityMainBinding binding;


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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        contentView = findViewById(R.id.contentContainer);
        loadingView = findViewById(R.id.progressBar);
        errorTextView = findViewById(R.id.errorTextView);
    }

    @SuppressLint("StaticFieldLeak")
    private void loadWeatherInfo() {
        loadAsyncTask = new AsyncTask<Void, Void, CurrentWeatherViewContent>() {
            private Exception exception;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                displayLoading();
            }

            @Override
            protected CurrentWeatherViewContent doInBackground(Void... voids) {
                try {
                    return new MainActivityPresenter().getCurrentWeather(CITY_ID_GYOR_MOSON_SOPRON_MEGYE);
                } catch (IOException e) {
                    exception = e;
                    return null;
                }
            }

            @Override
            protected void onPostExecute(CurrentWeatherViewContent currentWeatherViewContent) {
                super.onPostExecute(currentWeatherViewContent);
                loadAsyncTask = null;
                if (!isFinishing()) {
                    if (currentWeatherViewContent != null) {
                        displayContent(currentWeatherViewContent);
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

    private void displayContent(CurrentWeatherViewContent currentWeatherViewContent) {
        contentView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        errorTextView.setVisibility(View.GONE);

        binding.setCurrentWeather(currentWeatherViewContent);
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