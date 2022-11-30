package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView errorConnexionTextView;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button ConnectBtn;
    private TextView CreateAccountbtn;

    private String username;
    private String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorConnexionTextView = findViewById(R.id.errorConnexionTextView);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        ConnectBtn = findViewById(R.id.ConnectButton);
        CreateAccountbtn = findViewById(R.id.CreateAccountBtn);

        ConnectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();

                //LANCER LA REQUETE POUR CONNEXION DU USER


            }
        });

        CreateAccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createAccountActivity = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(createAccountActivity);
            }
        });

    }
}