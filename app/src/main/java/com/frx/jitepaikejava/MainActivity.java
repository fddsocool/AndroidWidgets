package com.frx.jitepaikejava;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.frx.jitepaikejava.SplitEditDemo.SplitEditDemoActivity;
import com.frx.jitepaikejava.noif.NoIfActivity;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.OVER_SCROLL_NEVER;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<DemoBean> arrayList = new ArrayList<>();
        addDemo(arrayList);
        RecyclerView recyclerView = findViewById(R.id.rlWidgets);
        List<IDelegateAdapter<DemoBean>> delegateAdapters = new ArrayList<>();
        UsageDelegate usageDelegate = new UsageDelegate(this);
        WidgetDelegate widgetDelegate = new WidgetDelegate(this);
        DivDelegate divDelegate = new DivDelegate(this);
        delegateAdapters.add(usageDelegate);
        delegateAdapters.add(widgetDelegate);
        delegateAdapters.add(divDelegate);
        DemoDelegateAdapter demoDelegateAdapter = new DemoDelegateAdapter(this, arrayList, delegateAdapters);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(demoDelegateAdapter);
        recyclerView.setOverScrollMode(OVER_SCROLL_NEVER);
        widgetDelegate.setOnItemClick((position, data) -> {
            Intent intent = new Intent(this, data.getIntentClass());
            startActivity(intent);
        });
        usageDelegate.setOnItemClick((position, data) -> {
            Intent intent = new Intent(this, data.getIntentClass());
            startActivity(intent);
        });
    }

    private void addDemo(ArrayList<DemoBean> arrayList) {
        DemoBean divWidget = new DemoBean();
        divWidget.setType(0);
        divWidget.setDemoName("控件");
        arrayList.add(divWidget);

        DemoBean splitEditDemo = new DemoBean();
        splitEditDemo.setType(1);
        splitEditDemo.setDemoName("分离式输入框");
        splitEditDemo.setDescription("用于验证码等场景");
        splitEditDemo.setIntentClass(SplitEditDemoActivity.class);
        arrayList.add(splitEditDemo);

        DemoBean divUsage = new DemoBean();
        divUsage.setType(0);
        divUsage.setDemoName("技巧");
        arrayList.add(divUsage);

        DemoBean noIfDemo = new DemoBean();
        noIfDemo.setType(2);
        noIfDemo.setDemoName("no-if的方法");
        noIfDemo.setDescription("用炫酷的方式改写if-else");
        noIfDemo.setIntentClass(NoIfActivity.class);
        arrayList.add(noIfDemo);
    }
}