package com.weavey.loadinglayout;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.weavey.loading.lib.LoadingLayout;

public class MainActivity extends AppCompatActivity {

    private LoadingLayout loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loading = (LoadingLayout) findViewById(R.id.loading);
        loading.setLoadingPage(R.layout.define_loading_page).setOnReloadListener(new LoadingLayout.OnReloadListener() {

            @Override
            public void onReload(View v) {

                Toast.makeText(MainActivity.this, "重试", Toast.LENGTH_SHORT).show();
            }
        });

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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loading.setStatus(LoadingLayout.Loading);
            }
        },10000);
    }
}
