package com.frx.jitepaikejava.NoIfElseDemo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.frx.jitepaikejava.R;
import com.frx.jitepaikejava.NoIfElseDemo.ResponsibilityChain.PayChain;
import com.frx.jitepaikejava.NoIfElseDemo.payFactory.AliaStrategyPay;
import com.frx.jitepaikejava.NoIfElseDemo.payFactory.IFactoryPay;
import com.frx.jitepaikejava.NoIfElseDemo.payFactory.JingStrategyDongPay;
import com.frx.jitepaikejava.NoIfElseDemo.payFactory.PayStrategyFactory;
import com.frx.jitepaikejava.NoIfElseDemo.payFactory.WeixinStrategyPay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class NoIfActivity extends AppCompatActivity {

    private ConstraintLayout mClTitle;
    private TextView mTvTitle;
    private ImageView mImgBack;

    //----doWithAnnotation
    private TextView mTvAnnotations;
    private HashMap<String, IPay> mPayHashMap;
    //----doWithAnnotation

    //----doWithSupport
    private TextView mTvSupport;
    private ArrayList<IPaySupport> mPaySupports;
    //----doWithSupport

    //----doWithStrategicFactory
    private TextView mTvStrategicFactoryModel;
    //----doWithStrategicFactory

    //----doResponsibilityChain
    private TextView mTvResponsibilityChain;
    //----doResponsibilityChain

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_if);

        initView();
    }

    private void initView() {
        mClTitle = (ConstraintLayout) findViewById(R.id.clTitle);
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mImgBack = (ImageView) findViewById(R.id.imgBack);
        mTvAnnotations = (TextView) findViewById(R.id.tvAnnotations);
        mTvSupport = (TextView) findViewById(R.id.tvSupport);
        mTvStrategicFactoryModel = (TextView) findViewById(R.id.tvStrategicFactoryModel);
        mTvResponsibilityChain = (TextView) findViewById(R.id.tvResponsibilityChain);

        mTvTitle.setText("优化if-else");
        mImgBack.setOnClickListener(v -> finish());

        //----doWithAnnotation
        mTvAnnotations.setOnClickListener(v -> doWithAnnotation());
        //----doWithAnnotation

        //----doWithSupport
        mTvSupport.setOnClickListener(v -> doWithSupport());
        //----doWithSupport

        //----doWithStrategicFactory
        mTvStrategicFactoryModel.setOnClickListener(v -> doWithStrategicFactory());
        //----doWithStrategicFactory

        //----doResponsibilityChain
        mTvResponsibilityChain.setOnClickListener(v -> doResponsibilityChain());
        //----doResponsibilityChain
    }

    //----doWithAnnotation
    private void doWithAnnotation() {
        if (mPayHashMap == null) {
            mPayHashMap = new HashMap<>();
            AliaPay aliaPay = new AliaPay();
            PayCode aliaPayCode = aliaPay.getClass().getAnnotation(PayCode.class);
            WeixinPay weixinPay = new WeixinPay();
            PayCode weixinPayCode = weixinPay.getClass().getAnnotation(PayCode.class);
            JingDongPay jingdongPay = new JingDongPay();
            PayCode jingdongPayCode = jingdongPay.getClass().getAnnotation(PayCode.class);
            mPayHashMap.put(aliaPayCode.value(), aliaPay);
            mPayHashMap.put(weixinPayCode.value(), weixinPay);
            mPayHashMap.put(jingdongPayCode.value(), jingdongPay);
        }
        ArrayList<String> payCode = new ArrayList<>(mPayHashMap.keySet());
        mPayHashMap.get(payCode.get(new Random().nextInt(3))).pay();
    }
    //----doWithAnnotation

    //----doWithSupport
    private void doWithSupport() {
        if (mPaySupports == null) {
            mPaySupports = new ArrayList<>();
            AliaPaySupport aliaPaySupport = new AliaPaySupport();
            WeixinPaySupport weixinPaySupport = new WeixinPaySupport();
            JingDongPaySupport jingDongPaySupport = new JingDongPaySupport();
            mPaySupports.add(aliaPaySupport);
            mPaySupports.add(weixinPaySupport);
            mPaySupports.add(jingDongPaySupport);

        }
        ArrayList<String> payCode = new ArrayList<>();
        payCode.add("alia");
        payCode.add("weixin");
        payCode.add("jingdong");
        String code = payCode.get(new Random().nextInt(3));

        for (IPaySupport paySupport : mPaySupports) {
            if (paySupport.support(code)) {
                paySupport.pay();
            }
        }
    }
    //----doWithSupport

    //----doWithStrategicFactory
    private void doWithStrategicFactory() {
        ArrayList<IFactoryPay> iFactoryPays = new ArrayList<>();
        iFactoryPays.add(new AliaStrategyPay());
        iFactoryPays.add(new WeixinStrategyPay());
        iFactoryPays.add(new JingStrategyDongPay());
        PayStrategyFactory payStrategyFactory = new PayStrategyFactory(iFactoryPays);
        for (IFactoryPay iFactoryPay : iFactoryPays) {
            iFactoryPay.init(payStrategyFactory);
        }
        ArrayList<String> payCode = new ArrayList<>();
        payCode.add("alia");
        payCode.add("weixin");
        payCode.add("jingdong");
        payStrategyFactory.get(payCode.get(new Random().nextInt(3))).pay();
    }
    //----doWithStrategicFactory

    //----doResponsibilityChain
    private void doResponsibilityChain() {
        ArrayList<String> payCode = new ArrayList<>();
        payCode.add("alia");
        payCode.add("weixin");
        payCode.add("jingdong");
        payCode.add("xiaomi");
        PayChain payChain = new PayChain();
        payChain.execute(payCode.get(new Random().nextInt(3)));
    }
    //----doResponsibilityChain
}