package fragmentexample.android.attrecto.com.fragmentexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openDetail(String param){
        FrameLayout detailContainer = findViewById(R.id.detailFragmentContainer);
        if(detailContainer == null) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("parameter", param);
            startActivity(intent);
        }else{
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setParam(param);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detailFragmentContainer, detailFragment)
                    .commit();
        }
    }
}
