package com.attrecro.example03;


import android.text.TextUtils;

public class TextManipulator {
    private String mExampleMessage;

    public TextManipulator(String mExampleMessage) {
        this.mExampleMessage = mExampleMessage;
    }

    public String getAllCapital(){
        return mExampleMessage.toUpperCase();
    }
}
