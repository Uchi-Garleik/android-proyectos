package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.listUsers.view.ListUsersActivity;
import com.example.myapplication.listproducts.view.ListMyProductsActivity;

public class HomeActivity extends AppCompatActivity {
    SharedPreferences sharedPreferencesUserCFG;

    Button buttonLogout;
    Button buttonListProducts;
    Button buttonMostSells;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initComponents();
    }
    private boolean isLoggedIn(){
        return sharedPreferencesUserCFG.getBoolean("isLoggedIn", false);
    }
    public void initComponents(){
        sharedPreferencesUserCFG = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        buttonLogout = findViewById(R.id.logoutBtn);
        buttonListProducts = findViewById(R.id.listadoProductosBtn);
        buttonMostSells = findViewById(R.id.mostSellsBtn);

        buttonMostSells.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListUsersActivity.class);
            startActivity(intent);
        });

        buttonListProducts.setOnClickListener(view -> {
        if (!isLoggedIn()){
            Toast.makeText(this, "Please Log In", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(this, ListMyProductsActivity.class);
                startActivity(intent);
            }
        });

        buttonLogout.setOnClickListener(view -> {
        SharedPreferences.Editor editor = sharedPreferencesUserCFG.edit();
            editor.remove("isLoggedIn");
            editor.remove("username");
            editor.remove("id");
            editor.apply();
        });
    }
}
