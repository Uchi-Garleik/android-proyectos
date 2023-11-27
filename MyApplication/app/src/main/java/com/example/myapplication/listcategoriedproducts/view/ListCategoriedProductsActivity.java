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
import com.example.myapplication.buyproduct.view.BuyProductActivity;
import com.example.myapplication.listcategoriedproducts.ContractListCategoriedProducts;
import com.example.myapplication.listcategoriedproducts.presenter.ListCategoriedProductsPresenter;

import java.util.ArrayList;

public class ListCategoriedProductsActivity extends AppCompatActivity implements ContractListCategoriedProducts.View, RecyclerViewInterface {


    private SharedPreferences sharedPreferences;

    Button menBtn;
    Button womenBtn;

    private ArrayList<String> categoryArray;
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
        categoryArray = new ArrayList<>();
        Producto producto = new Producto();
        producto.setCategoria("");
        menBtn = findViewById(R.id.menBtn);
        womenBtn = findViewById(R.id.womenBtn);

        // TODO: ARRAYLIST THAT STORES FILTERS

        menBtn.setOnClickListener(v -> {

            if (categoryArray.contains("men")){
                categoryArray.remove("men");
            }else{
                categoryArray.add("men");
            }
            Log.e("initComponents: ", "MY FILTERS FOR PRODUCTS:" + categoryArray );
            producto.setCategoryArrayList(categoryArray);
            presenter.listCategoriedProducts(producto);
        });

        womenBtn.setOnClickListener(v -> {
            if (categoryArray.contains("women")){
                categoryArray.remove("women");
            }else{
                categoryArray.add("women");
            }
            Log.e("initComponents: ", "MY FILTERS FOR PRODUCTS:" + categoryArray );
            producto.setCategoryArrayList(categoryArray);
            presenter.listCategoriedProducts(producto);
        });

        presenter.listCategoriedProducts(producto);
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
        Intent intent = new Intent(this, BuyProductActivity.class);
        intent.putExtra("nombre", productoArrayListRecycler.get(position).getNombre());
        intent.putExtra("descripcion", productoArrayListRecycler.get(position).getNombre());
        intent.putExtra("imagePath", productoArrayListRecycler.get(position).getImagePath());
        startActivity(intent);
    }
}
