package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.vending.VendingActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
    }

    private void initComponents() {
        Button button = findViewById(R.id.loginBtn2);
        button.setOnClickListener(view -> login(button));
    }

    private void login(Button button){
        Intent intent = new Intent(this, VendingActivity.class);
        startActivity(intent);
    }

}
