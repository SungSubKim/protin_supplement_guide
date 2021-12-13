package com.google.sample.cloudvision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class SplashActivity extends Activity {
    ImageView img_v;
    boolean is_started;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main00);
        Handler starter =  new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(4000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                starter.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!is_started) {
                            startActivity(new Intent(getApplicationContext(), MainActivity0.class));
                            is_started = true;
                        }

                    }
                });
            }
        }).start();
        img_v = findViewById(R.id.id_muscle);
        img_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!is_started) {
                    startActivity(new Intent(getApplicationContext(), MainActivity0.class));
                    is_started = true;
                }
            }
        });
//        startActivity(new Intent(getApplicationContext(),MainActivity0.class));
//        finish();
    }
}
