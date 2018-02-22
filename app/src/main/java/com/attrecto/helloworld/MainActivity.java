package com.attrecto.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvHello = (TextView)findViewById(R.id.tvHello);
        tvHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hello(v);
            }
        });
    }

    public void hello(View view) {
        Toast.makeText(this, "Hello World :)", Toast.LENGTH_SHORT).show();
    }
}
