package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.listcategoriedproducts.view.ListCategoriedProductsActivity;
import com.example.myapplication.listrateusers.view.ListUsersActivity;

public class HomeActivity extends AppCompatActivity {


    Button usuariosBtn;
    Button productsBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initComponents();
    }

    private void initComponents() {
        usuariosBtn = findViewById(R.id.usersMenuBtn);
        productsBtn = findViewById(R.id.categoryMenuBtn);

        productsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListCategoriedProductsActivity.class);
            intent.putExtra("categoria","pantal");
            startActivity(intent);
        });

        usuariosBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListUsersActivity.class);
            startActivity(intent);
        });
    }
}
