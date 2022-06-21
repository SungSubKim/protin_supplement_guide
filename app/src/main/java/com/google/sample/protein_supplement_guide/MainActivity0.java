package com.google.sample.protein_supplement_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity0 extends AppCompatActivity {

    private Button btn_move,btn_move2,btn_move3,btn_move4;
    private EditText et_test;
    private String str;
    private ImageView test;
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);

//        et_test = findViewById(R.id.et_test);

        btn_move = findViewById(R.id.btn_move);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity0.this, MainActivity.class)); // Activity이동
            }
        });
        btn_move2 = findViewById(R.id.btn_move2);
        btn_move2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity0.this, SubActivity2.class)); // Activity이동
            }
        });
        btn_move3 = findViewById(R.id.btn_move3);
        btn_move3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity0.this, SubActivity3.class)); // Activity이동
            }
        });
        btn_move4 = findViewById(R.id.btn_move4);
        btn_move4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity0.this, SubActivity4.class)); // Activity이동
            }
        });

//        test = (ImageView)findViewById(R.id.test);
//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "very good", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
