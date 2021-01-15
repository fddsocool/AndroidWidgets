package com.frx.jitepaikejava.SplitEditDemo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.frx.jitepaikejava.R;

public class SplitEditDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_edit_demo);

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