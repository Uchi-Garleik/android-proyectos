package com.example.myapplication.buyproduct.presenter;

import android.content.Context;

import com.example.myapplication.beans.Producto;
import com.example.myapplication.buyproduct.ContractBuyProduct;
import com.example.myapplication.buyproduct.model.BuyProductModel;
import com.example.myapplication.listcategoriedproducts.ContractListCategoriedProducts;
import com.example.myapplication.listcategoriedproducts.model.ListCategoriedProductsModel;

import java.util.ArrayList;

public class BuyProductPresenter implements ContractBuyProduct.Presenter, ContractBuyProduct.Model.OnBuyProductListener {
    private ContractBuyProduct.View view;
    private ContractBuyProduct.Model model;

    public BuyProductPresenter(ContractBuyProduct.View view){
        this.view = view;
        model = new BuyProductModel(this, (Context) this.view);
    }

    @Override
    public void buyProduct(Producto producto) {
        model.buyProductApi(producto, this);
    }

    @Override
    public void onFinished(ArrayList<Producto> productsList) {
        view.successBuyProduct(productsList);
    }

    @Override
    public void onFailure(String err) {

    }
}
