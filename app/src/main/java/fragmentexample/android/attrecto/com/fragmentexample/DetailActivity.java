package fragmentexample.android.attrecto.com.fragmentexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Copyright (c) 2018 Apps AS. All rights reserved.
 * Created by gabor.demko on 2018.04.13..
 */

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String param = getIntent().getStringExtra("parameter");
        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        detailFragment.setParam(param);
    }

}
