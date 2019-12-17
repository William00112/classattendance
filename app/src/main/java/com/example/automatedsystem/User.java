package com.example.automatedsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


@SuppressWarnings("deprecation")
public class User extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        Button btn1 = findViewById(R.id.att);

        btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(User.this, Units.class);
                startActivity(intent1);
            }
        });
    }
}

