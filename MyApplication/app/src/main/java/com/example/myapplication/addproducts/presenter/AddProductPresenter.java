package com.example.myapplication.addproducts.presenter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.addproducts.ContractAddProducts;
import com.example.myapplication.addproducts.model.AddProductModel;
import com.example.myapplication.beans.Producto;

public class AddProductPresenter extends AppCompatActivity implements ContractAddProducts.Presenter, ContractAddProducts.Model.OnAddProductListener {

    private ContractAddProducts.View view;
    private ContractAddProducts.Model model;

    public AddProductPresenter(ContractAddProducts.View view){
        this.view = view;
        model = new AddProductModel(this);
    }

    @Override
    public void addProduct(Producto producto) {
        model.addProductsApi(producto, this);
    }

    @Override
    public void onFinished(Producto producto) {
        view.successAddProduct(producto);
    }

    @Override
    public void onFailure(String err) {

    }
}
