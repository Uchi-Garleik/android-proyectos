package com.example.myapplication.listproducts.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import com.example.myapplication.beans.Producto;
import com.example.myapplication.listproducts.ContractListProducts;
import com.example.myapplication.listproducts.presenter.ListProductsPresenter;

import java.util.ArrayList;

public class ListProductsActivity extends AppCompatActivity implements ContractListProducts.View {




    private ListProductsPresenter presenter = new ListProductsPresenter(this);

    private static ListProductsActivity listProductsActivity = null;
    private static ListProductsActivity getInstance(){
        return listProductsActivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);
        listProductsActivity = this;
        initComponents();
    }

    private void initComponents() {
        Producto producto = new Producto();
        presenter.listProducts(producto);
    }

    @Override
    public void successListProducts(ArrayList<Producto> productsList) {

    }

    @Override
    public void failureMovies(String err) {

    }
}
