package example.android.attrecto.com.asynch2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AsyncTask<Void, Void, String> asyncTask;

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
    }

    @SuppressLint("StaticFieldLeak")
    private void startBackgroundOperation(){
        asyncTask = new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... voids) {
                //you can not touch the UI from this method, it will crash
                return doSomethingSlow();
            }

            @Override
            protected void onPostExecute(String result) {
                asyncTask = null;

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Result")
                        .setMessage(result)
                        .setPositiveButton("OK", null)
                        .show();
            }

            @Override
            protected void onCancelled() {
                asyncTask = null;
            }
        };

        asyncTask.execute();
    }

    /**
     * Simulating a slow operation for the tests sake
     */
    private String doSomethingSlow(){
        for(int i=0; i <= 10; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //no problem its just a simulation
                e.printStackTrace();
            }
        }
        return "This is the result of the slow task";
    }
}
