package com.example.myapplication.buyproduct.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.material.imageview.ShapeableImageView;

public class BuyProductActivity extends AppCompatActivity {

    private TextView productTitleText;
    private TextView productDescriptionText;
    private ShapeableImageView shapeableImageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productscreen);
        initComponents();
    }

    private void initComponents() {
        productTitleText = findViewById(R.id.productScreenTitle);
        productTitleText.setText(getIntent().getStringExtra("nombre"));
    }
}
