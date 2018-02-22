package com.attrecto.barkochba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int number;
    private EditText etNumber;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = findViewById(R.id.et_number);
        btnOk = findViewById(R.id.btn_guess);
        generateRandomNumber();
    }

    private void generateRandomNumber() {
        Random r = new Random(System.currentTimeMillis());
        number = r.nextInt(20) + 1;
    }

    public void onOk(View view) {
        int number;
        try {
            number = Integer.parseInt(etNumber.getText().toString());
        } catch (NumberFormatException ex) {
            showResult(getString(R.string.numberformatexception));
            clearInput();
            return;
        }
        clearInput();
        if (number < this.number) {
            showResult(getString(R.string.bigger));
        } else if (number > this.number) {
            showResult(getString(R.string.smaller));
        } else {
            showResult(getString(R.string.congrats));
            btnOk.setEnabled(false);
        }
    }

    public void onNew(View view) {
        generateRandomNumber();
        showResult(getString(R.string.go));
        clearInput();
        btnOk.setEnabled(true);
    }

    private void showResult(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void clearInput() {
        etNumber.setText("");
    }
}
