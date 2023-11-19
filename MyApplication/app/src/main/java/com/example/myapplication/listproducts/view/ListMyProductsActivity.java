package com.example.myapplication.listproducts.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;

import com.example.myapplication.beans.Producto;
import com.example.myapplication.listproducts.ContractListMyProducts;
import com.example.myapplication.listproducts.presenter.ListMyMyProductsPresenter;
import com.example.myapplication.loginuser.view.LoginUserActivity;

import java.util.ArrayList;

public class ListMyProductsActivity extends AppCompatActivity implements ContractListMyProducts.View {

    Button backButton;

    private ListMyMyProductsPresenter presenter = new ListMyMyProductsPresenter(this);

    private static ListMyProductsActivity listMyProductsActivity = null;

    private static ListMyProductsActivity getInstance(){
        if (listMyProductsActivity == null){
            listMyProductsActivity = new ListMyProductsActivity();
        }
        return listMyProductsActivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);
        listMyProductsActivity = this;
        initComponents();
    }

    private void initComponents() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Log.e("user_id","hello");
        Producto producto = new Producto();
        presenter.listMyProducts(producto);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginUserActivity.class);
            startActivity(intent);
        });
    }

    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    @Override
    public void successListMyProducts(ArrayList<Producto> productsList) {
        LinearLayout columnLeft = findViewById(R.id.columnLeft);
        LinearLayout columnRight = findViewById(R.id.columnRight);
        LinearLayout parentElement = null;
        int counter = 0;

        for (Producto producto : productsList) {
            Log.e("user_prod_id", "id:" + producto.getIdUser());
            if (counter == 0){
                parentElement = columnLeft;
                Log.i("Counter", "hhello");
                counter = 1;
            }else{
                parentElement = columnRight;
                counter = 0;
                Log.i("Counter", "hi");
            }

            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(dpToPx(160), dpToPx(160)));
            layoutParams.setMargins(0,dpToPx(5),0,0);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setId(View.generateViewId());
            linearLayout.setGravity(Gravity.BOTTOM);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
            TextView textView = new TextView(this);
            textView.setId(View.generateViewId());
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(40)));
            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlue));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setText(producto.getNombre());

            linearLayout.addView(textView);
            parentElement.addView(linearLayout);
        }
    }

    @Override
    public void failureMovies(String err) {

    }
}
