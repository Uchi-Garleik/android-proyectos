package com.example.myapplication.listcategoriedproducts.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewInterface;
import com.example.myapplication.adapters.ProductsAdapter;
import com.example.myapplication.beans.Producto;
import com.example.myapplication.listcategoriedproducts.ContractListCategoriedProducts;
import com.example.myapplication.listcategoriedproducts.presenter.ListCategoriedProductsPresenter;

import java.util.ArrayList;

public class ListCategoriedProductsActivity extends AppCompatActivity implements ContractListCategoriedProducts.View, RecyclerViewInterface {


    private SharedPreferences sharedPreferences;

    Button menBtn;
    Button womenBtn;

    private ArrayList<Producto> productoArrayList;
    private ListCategoriedProductsPresenter presenter = new ListCategoriedProductsPresenter(this);
    private static ListCategoriedProductsActivity listCategoriedProductsActivity = null;

    private RecyclerView recyclerView;
    private ArrayList<Producto> productoArrayListRecycler;


    public static ListCategoriedProductsActivity getInstance() {
        if (listCategoriedProductsActivity == null){
            listCategoriedProductsActivity = new ListCategoriedProductsActivity();
        }
        return listCategoriedProductsActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorieditems);
        listCategoriedProductsActivity = this;
        sharedPreferences = getSharedPreferences("com.MyApp.PRODUCTS",MODE_PRIVATE);
        initComponents();
    }

    private void initComponents() {
        Producto producto = new Producto();
        producto.setCategoria("Clothing");
        presenter.listCategoriedProducts(producto);
        menBtn = findViewById(R.id.menBtn);
        womenBtn = findViewById(R.id.womenBtn);

        // TODO: ARRAYLIST THAT STORES FILTERS

        menBtn.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FILTER", "");
            editor.apply();

            Log.e("initComponents: ", "MY FILTERS FOR PRODUCTS:" + sharedPreferences.getString("FILTER","") );
        });

        womenBtn.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String currentFilter = sharedPreferences.getString("FILTER", "");

            if (!currentFilter.isEmpty()) {
                // Check if the current filter contains "women"
                if (currentFilter.contains("women")) {
                    Log.e("initComponents: ", "wtfqweqe");
                    // Remove "women" from the current filter
                    currentFilter = currentFilter.replace("&women", "");
                    // If "women" was the only filter, you might want to handle the case where the resulting string is empty
                } else {
                    // Add "women" to the current filter
                    currentFilter += "&women";
                }
            } else {
                // If the current filter is empty, set it to "women"
                currentFilter = "women";
            }

            // Update the shared preferences with the modified filter
            editor.putString("FILTER", currentFilter);
            editor.apply();
            Log.e("initComponents: ", "MY FILTERS FOR PRODUCTS:" + sharedPreferences.getString("FILTER","") );
        });


    }

    @Override
    public void successListCategoriedProducts(ArrayList<Producto> productsList) {
        Log.e("successListCategoriedProducts: ", "MY ARRAY:" + productsList );
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
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
    public void failureListCategoriedProducts(String err) {

    }

    @Override
    public void OnItemClick(int position) {

    }
}
