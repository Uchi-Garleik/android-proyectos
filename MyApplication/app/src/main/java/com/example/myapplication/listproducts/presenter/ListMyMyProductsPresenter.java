package com.example.myapplication.listproducts.presenter;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.beans.Producto;
import com.example.myapplication.listproducts.ContractListMyProducts;
import com.example.myapplication.listproducts.model.ListMyProductsModel;

import java.util.ArrayList;

public class ListMyMyProductsPresenter extends AppCompatActivity implements ContractListMyProducts.Presenter, ContractListMyProducts.Model.OnListMyProductsListener {
    private ContractListMyProducts.View view;
    private ContractListMyProducts.Model model;

    public ListMyMyProductsPresenter(ContractListMyProducts.View view){
        this.view = view;
        model = new ListMyProductsModel(this, (Context) this.view);
    }

    @Override
    public void listMyProducts(Producto producto) {
        model.listMyProductsApi(producto, this);
    }

    @Override
    public void onFinished(ArrayList<Producto> productsList) {
        view.successListMyProducts(productsList);
    }

    @Override
    public void onFailure(String err) {

    }
}
