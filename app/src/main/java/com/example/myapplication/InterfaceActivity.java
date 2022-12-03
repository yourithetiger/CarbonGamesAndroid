package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class InterfaceActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        welcomeTextView = findViewById(R.id.welcometextView);

        username = getIntent().getStringExtra("username");

        welcomeTextView.setText("Bienvenue : " + username);
    }
}