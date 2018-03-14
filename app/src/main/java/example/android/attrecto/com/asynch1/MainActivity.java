package example.android.attrecto.com.asynch1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        doSomethingSlow();

        /*
        Notice that we set the contentView first,
        but until the slow operation finished the UI won't be shown
         */
    }

    /**
     * Simulating a slow operation for the tests sake
     */
    private void doSomethingSlow(){
        for(int i=0; i <= 10; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //no promlem its just a simulation
                e.printStackTrace();
            }
        }
    }
}
