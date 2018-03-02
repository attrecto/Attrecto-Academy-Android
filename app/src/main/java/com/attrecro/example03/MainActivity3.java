package com.attrecro.example03;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.attrecro.example03.databinding.ActivityMain3Binding;
import com.attrecro.example03.databinding.ActivityMain4Binding;

public class MainActivity3 extends AppCompatActivity {
    private String mCapturedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ActivityMain3Binding binding = DataBindingUtil.setContentView(this,R.layout.activity_main3);
        ActivityMain3Binding binding = ActivityMain3Binding.inflate(getLayoutInflater());
        //ActivityMain4Binding bindingWithViewModel = DataBindingUtil.setContentView(this,R.layout.activity_main4);

        Intent intent = getIntent();
        mCapturedMessage = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextManipulator textManipulator = new TextManipulator(mCapturedMessage);

        binding.setData(getCapturedMessage());
        //bindingWithViewModel.setViewModel(textManipulator);


    }

    public String getCapturedMessage() {
        return mCapturedMessage;
    }
}
