package com.example.myapplication.listproducts.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewInterface;
import com.example.myapplication.adapters.ProductsAdapter;
import com.example.myapplication.addproducts.view.AddProductActivity;
import com.example.myapplication.beans.Producto;
import com.example.myapplication.buyproduct.view.BuyProductActivity;
import com.example.myapplication.listcategoriedproducts.view.ListCategoriedProductsActivity;
import com.example.myapplication.listproducts.ContractListMyProducts;
import com.example.myapplication.listproducts.presenter.ListMyMyProductsPresenter;
import com.example.myapplication.listrateusers.view.ListUsersActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements ContractListMyProducts.View, RecyclerViewInterface {

    private ListMyMyProductsPresenter presenter = new ListMyMyProductsPresenter(this);
    private static HomeActivity listMyProductsActivity = null;

    Button homeBtn;
    Button usuariosBtn;
    Button productsBtn;
    Button addProductMenuBtn;

    private RecyclerView recyclerView;

    private ArrayList<Producto> productoArrayListRecycler;

    private static HomeActivity getInstance(){
        if (listMyProductsActivity == null){
            listMyProductsActivity = new HomeActivity();
        }
        return listMyProductsActivity;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listMyProductsActivity = this;
        initComponents();
    }

    private void initComponents() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Producto producto = new Producto();
        presenter.listMyProducts(producto);

        homeBtn = findViewById(R.id.homeMenuBtn);
        usuariosBtn = findViewById(R.id.usersMenuBtn);
        productsBtn = findViewById(R.id.categoryMenuBtn);
        addProductMenuBtn = findViewById(R.id.addProductMenuBtn);

        productsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListCategoriedProductsActivity.class);
            intent.putExtra("categoria","pantal");
            startActivity(intent);
        });

        usuariosBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListUsersActivity.class);
            startActivity(intent);
        });

        addProductMenuBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddProductActivity.class);
            startActivity(intent);
        });

    }

    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }


    @Override
    public void successListMyProducts(ArrayList<Producto> productsList) {
        dataInitialize(productsList);
        recyclerView = findViewById(R.id.productsColumn);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ProductsAdapter productsAdapter = new ProductsAdapter(this, productoArrayListRecycler, this);
        recyclerView.setAdapter(productsAdapter);
        productsAdapter.notifyDataSetChanged();

    }

    private void dataInitialize(ArrayList<Producto> productsList) {
        productoArrayListRecycler = new ArrayList<>();
        for (Producto producto : productsList) {
            Producto productoAux = producto;
            productoArrayListRecycler.add(productoAux);
        }
        Log.e("dataInitialize: ", "MY ARRAY:" + productoArrayListRecycler );
    }

    @Override
    public void failureListMyProducts(String err) {

    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(this, BuyProductActivity.class);
        intent.putExtra("nombre", productoArrayListRecycler.get(position).getNombre());
        intent.putExtra("descripcion", productoArrayListRecycler.get(position).getNombre());
        intent.putExtra("imagePath", productoArrayListRecycler.get(position).getImagePath());
        startActivity(intent);
    }
}
