package com.attrecro.example03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.attrecto.example03.MESSAGE";
    private EditText testMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testMessage = findViewById(R.id.et_test_message);
        Button startActivity2 = findViewById(R.id.btn_start_activity_2);
        Button startActivity3 = findViewById(R.id.btn_start_activity_3);

        startActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidTestMessage()){;
                    startActivity(MainActivity2.class);
                }
            }
        });

        startActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidTestMessage()){
                    startActivity(MainActivity3.class);
                }
            }
        });
    }


    private void startActivity(Class activity){
        Intent intent = new Intent(this,activity);
        intent.putExtra(EXTRA_MESSAGE,testMessage.getText().toString());
        startActivity(intent);
    }

    private boolean isValidTestMessage(){
        if(TextUtils.isEmpty(testMessage.getText().toString())){
            Toast.makeText(this,"First please type some text in the EditText",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }


}
