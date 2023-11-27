package com.example.myapplication.listcategoriedproducts.presenter;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.beans.Producto;
import com.example.myapplication.listcategoriedproducts.ContractListCategoriedProducts;
import com.example.myapplication.listcategoriedproducts.model.ListCategoriedProductsModel;

import java.util.ArrayList;

public class ListCategoriedProductsPresenter extends AppCompatActivity implements ContractListCategoriedProducts.Presenter, ContractListCategoriedProducts.Model.OnListCategoriedProductsListener {
    private ContractListCategoriedProducts.View view;
    private ContractListCategoriedProducts.Model model;

    public ListCategoriedProductsPresenter(ContractListCategoriedProducts.View view){
        this.view = view;
        model = new ListCategoriedProductsModel(this, (Context) this.view);
    }

    @Override
    public void listCategoriedProducts(Producto producto) {
        model.listCategoriedProductsApi(producto, this);
    }

    @Override
    public void onFinished(ArrayList<Producto> productsList) {
        view.successListCategoriedProducts(productsList);
    }

    @Override
    public void onFailure(String err) {

    }
}
