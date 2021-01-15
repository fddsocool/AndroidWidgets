package com.frx.jitepaikejava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SplitEditText splitEditText = findViewById(R.id.splitEditText1);
        splitEditText.setOnTextInputListener(new SplitEditText.OnTextInputListener() {
            @Override
            public void onTextInputChanged(String text, int length) {
                Log.d("fmg===>", "onTextInputChanged:" + text);
            }

            @Override
            public void onTextInputCompleted(String text) {
                Log.d("fmg===>", "onTextInputCompleted:" + text);
            }
        });
    }
}