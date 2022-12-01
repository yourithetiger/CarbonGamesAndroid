package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "jdbc:mysql://yourithetiger.cf:3306/nsi";
    private static final String USER = "youri";
    private static final String PASSWORD = "admin";
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
    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, Map<String, String>> {
        @Override
        protected Map<String, String> doInBackground(Void... voids) {
            Map<String, String> info = new HashMap<>();

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT name, address, phone_number FROM school_info LIMIT 1";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    info.put("name", resultSet.getString("name"));
                    info.put("address", resultSet.getString("address"));
                    info.put("phone_number", resultSet.getString("phone_number"));
                }
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading school information", e);
            }

            return info;
        }
        @Override
        protected void onPostExecute(Map<String, String> result) {
            if (!result.isEmpty()) {
                TextView textViewName = findViewById(R.id.textViewName);
                TextView textViewAddress = findViewById(R.id.textViewAddress);
                TextView textViewPhoneNumber = findViewById(R.id.textViewPhone);

                textViewName.setText(result.get("name"));
                textViewAddress.setText(result.get("address"));
                textViewPhoneNumber.setText(result.get("phone_number"));
            }
}