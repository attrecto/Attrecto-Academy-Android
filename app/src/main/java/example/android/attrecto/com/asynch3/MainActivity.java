package example.android.attrecto.com.asynch3;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AsyncTask<Void, Void, String> asyncTask;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        startBackgroundOperation();
    }

    private void setupUI() {
        findViewById(R.id.navigationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AnotherActivity.class));
                finish();
            }
        });
        progressBar = findViewById(R.id.progressBar);
    }

    @SuppressLint("StaticFieldLeak")
    private void startBackgroundOperation() {
        asyncTask = new AsyncTask<Void, Void, String>() {
            Exception exception = null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    return doSomethingSlow();
                } catch (Exception e) {
                    exception = e;
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                asyncTask = null;
                if(!isFinishing()) { //checking if the activity UI still exists
                    progressBar.setVisibility(View.GONE);
                    if (exception == null) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Result")
                                .setMessage(result)
                                .setPositiveButton("OK", null)
                                .show();
                    } else {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("ERROR")
                                .setMessage("Operation failed!")
                                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startBackgroundOperation();
                                    }
                                })
                                .setNegativeButton("OK", null)
                                .show();
                    }
                }
            }

            @Override
            protected void onCancelled() {
                asyncTask = null;
            }
        };

        asyncTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (asyncTask != null) {
            //cancelling the background operation when the activity is closed
            asyncTask.cancel(true);
        }
    }

    /**
     * Simulating a slow operation for the tests sake
     */
    private String doSomethingSlow() throws Exception {
        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //no problem its just a simulation
                e.printStackTrace();
            }
        }
        if (Math.random() < 0.3) {
            throw new Exception("Something went wrong");
        }
        return "This is the result of the slow task";
    }
}
