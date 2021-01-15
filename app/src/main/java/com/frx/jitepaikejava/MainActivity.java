package com.frx.jitepaikejava;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.frx.jitepaikejava.SplitEditDemo.SplitEditDemoActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<DemoBean> arrayList = new ArrayList<>();
        addDemo(arrayList);
        RecyclerView recyclerView = findViewById(R.id.rlWidgets);
        WidgetAdapter widgetAdapter = new WidgetAdapter(this, arrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(widgetAdapter);
        widgetAdapter.setOnDemoItemClickListener((position, demoBean) -> {
            Intent intent = new Intent(this, demoBean.getIntentClass());
            startActivity(intent);
        });
    }

    private void addDemo(ArrayList<DemoBean> arrayList) {
        DemoBean splitEditDemo = new DemoBean();
        splitEditDemo.setDemoName("分离式输入框");
        splitEditDemo.setDescription("用于验证码等场景");
        splitEditDemo.setIntentClass(SplitEditDemoActivity.class);
        arrayList.add(splitEditDemo);
    }
}