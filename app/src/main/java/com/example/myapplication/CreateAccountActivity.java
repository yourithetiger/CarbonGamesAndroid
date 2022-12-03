package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    private TextView connectPageBtn;
    private TextView errorCreateAccount;
    private Button createAccountBtn;

    private EditText usernameEditText;
    private EditText passwordEditText;

    private String username;
    private String password;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        connectPageBtn = findViewById(R.id.ConnectPageBtn);
        createAccountBtn = findViewById(R.id.CreateBtn);
        usernameEditText = findViewById(R.id.CreateUsernameEditText);
        passwordEditText = findViewById(R.id.CreatePasswordEditText);
        errorCreateAccount = findViewById(R.id.errorCreateTextView);

        databaseManager = new DatabaseManager(getApplicationContext());

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();

                //LANCER LA REQUETE POUR L'INSCRIPTION DU USER
                createAccount();


            }
        });

        connectPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent connectActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(connectActivity);
            }
        });


    }

    public void onApiResponse(JSONObject response){
        Boolean success = null;
        String error = "";

            try {
            success = response.getBoolean("success");

            if (success == true) {
                Intent interfaceActivity = new Intent(getApplicationContext(), InterfaceActivity.class);
                interfaceActivity.putExtra("username", username);
                startActivity(interfaceActivity);
                finish();

            } else {
                error = response.getString("error");
                errorCreateAccount.setVisibility(View.VISIBLE);
                errorCreateAccount.setText(error);
            }
        } catch (
        JSONException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

    }

    public void createAccount() {
        String url = "http://yourithetiger.cf/api/actions/createAccount.php";

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onApiResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        databaseManager.queue.add(jsonObjectRequest);
    }
}