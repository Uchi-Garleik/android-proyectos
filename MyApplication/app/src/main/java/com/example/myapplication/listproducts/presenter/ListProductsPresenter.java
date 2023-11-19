package com.example.myapplication.listproducts.presenter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.addproducts.ContractAddProducts;
import com.example.myapplication.addproducts.model.AddProductModel;
import com.example.myapplication.beans.Producto;
import com.example.myapplication.listproducts.ContractListProducts;
import com.example.myapplication.listproducts.model.ListProductsModel;

import java.util.ArrayList;

public class ListProductsPresenter extends AppCompatActivity implements ContractListProducts.Presenter, ContractListProducts.Model.OnListProductsListener {
    private ContractListProducts.View view;
    private ContractListProducts.Model model;

    public ListProductsPresenter(ContractListProducts.View view){
        this.view = view;
        model = new ListProductsModel(this);
    }

    @Override
    public void listProducts(Producto producto) {
        model.listProductsApi(producto, this);
    }

    @Override
    public void onFinished(ArrayList<Producto> productsList) {
        view.successListProducts(productsList);
    }

    @Override
    public void onFailure(String err) {

    }
}
