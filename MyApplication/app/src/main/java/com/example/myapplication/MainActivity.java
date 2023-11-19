package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.loginuser.view.LoginUserActivity;

public class MainActivity extends AppCompatActivity {

    private Boolean isViewVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(v -> goToLogin(loginBtn));

    }

    private void goToLogin(Button loginBtn){
        Intent intent = new Intent(this, LoginUserActivity.class);
        startActivity(intent);
    }




}