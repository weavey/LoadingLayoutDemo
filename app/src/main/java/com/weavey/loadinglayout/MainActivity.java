package com.weavey.loadinglayout;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.weavey.loading.lib.LoadingLayout;

public class MainActivity extends AppCompatActivity {

    private LoadingLayout loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loading = (LoadingLayout) findViewById(R.id.loading);

//        View view = LayoutInflater.from(this).inflate(R.layout.define_loading_page, null);
//        loading.setLoadingPage(view);
//        loading.setLoadingPage(R.layout.define_loading_page);

        loading.setStatus(LoadingLayout.Loading);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loading.setStatus(LoadingLayout.Empty);
            }
        },2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loading.setStatus(LoadingLayout.Error);
            }
        },4000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loading.setStatus(LoadingLayout.No_Network);
            }
        },6000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loading.setStatus(LoadingLayout.Success);
            }
        },8000);



    }
}
