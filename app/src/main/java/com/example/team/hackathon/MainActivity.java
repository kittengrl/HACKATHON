package com.example.team.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button mText, mbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (Button) findViewById(R.id.btn);
        mbtn = (Button) findViewById(R.id.btn1);
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(MainActivity.this, post_activity.class);
               startActivity(i);
            }
        });
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(MainActivity.this, posted_activity.class);
                startActivity(ii);
            }
        });
    }
}