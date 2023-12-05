package com.example.myapplication.listproducts.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.UUID;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);
        listMyProductsActivity = this;
        initComponents();
    }

    private void initComponents() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
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
            if (counter == 0){
                parentElement = columnLeft;
                counter = 1;
            }else{
                parentElement = columnRight;
                counter = 0;
            }

            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(dpToPx(160), dpToPx(160)));
            layoutParams.setMargins(0,dpToPx(5),0,0);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setId(View.generateViewId());
//            linearLayout.setGravity(Gravity.BOTTOM);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
//            linearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));

            ImageView imageView = new ImageView(this);
            imageView.setId(View.generateViewId());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(120)));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            String uniqueID = UUID.randomUUID().toString();
//            Picasso.get().load("http://192.168.1.196:8080"+producto.getImagePath()+"?" + uniqueID).into(imageView);
            Picasso.get().load("http://192.168.104.75:8080"+producto.getImagePath()+"?" + uniqueID).into(imageView);
//            Picasso.get().load(producto.getImagePath()+"?" + uniqueID).into(imageView);
            Log.e("successListMyProducts: ", producto.getImagePath() );
            TextView textView = new TextView(this);
            textView.setId(View.generateViewId());
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(40)));
            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setTextColor(ContextCompat.getColor(this, R.color.white));
            textView.setText(producto.getNombre());
            linearLayout.addView(imageView);
            linearLayout.addView(textView);
            parentElement.addView(linearLayout);
        }
    }

    @Override
    public void failureListMyProducts(String err) {

    }
}
