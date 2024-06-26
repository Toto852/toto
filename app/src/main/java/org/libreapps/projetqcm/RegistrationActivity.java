package org.libreapps.projetqcm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName, userPassword, userEmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        userName = (EditText) findViewById(R.id.reg_user_name);
        userEmail = (EditText) findViewById(R.id.reg_user_email);
        userPassword = (EditText) findViewById(R.id.reg_user_password);
        Button buttonRegistration = (Button) findViewById(R.id.btn_register_2);

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ConnectionRest connectionRest = new ConnectionRest();
                    JSONObject jsonAuthentification = new JSONObject();
                    jsonAuthentification.put("name", userName.getText());
                    jsonAuthentification.put("email", userEmail.getText());
                    jsonAuthentification.put("password", userPassword.getText());
                    jsonAuthentification.put("licence", "<LICENCE>");
                    connectionRest.setObj(jsonAuthentification);
                    connectionRest.execute("CREATE_USER");
                    String token = connectionRest.get();

                    if(token.charAt(0)=='{') {
                        Log.v("LoginActivity", token);
                    }else {
                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                        startActivity(intent);
                        Param.getInstance().setToken(token);
                    }
                } catch (JSONException e1) {
                    Log.v("TAG", "[JSONException] e : " + e1.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}