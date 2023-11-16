package com.example.myapplication.vending;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class VendingActivity extends AppCompatActivity {

    String[] categories = {"Zapatillas", "Pantalones", "Chaqueta", "Abrigos"};
    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listitem);
        initComponents();
    }

    private void initComponents() {
        autoCompleteTextView = findViewById(R.id.autoCompleteText);
        adapterCategories = new ArrayAdapter<String>(this, R.layout.list_item, categories);
        autoCompleteTextView.setAdapter(adapterCategories);

        
        
    }
}
