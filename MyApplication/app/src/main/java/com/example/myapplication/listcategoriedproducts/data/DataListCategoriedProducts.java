package com.example.myapplication.listcategoriedproducts.data;

import com.example.myapplication.beans.Producto;

import java.util.ArrayList;

public class DataListCategoriedProducts {
    private String message;
    private ArrayList<Producto> productsList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Producto> getProductsList() {
        return productsList;
    }

    public void setProductList(ArrayList<Producto> productList) {
        this.productsList = productList;
    }
}
