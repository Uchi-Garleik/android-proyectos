package com.example.myapplication.addproducts.data;

import com.example.myapplication.beans.Producto;

import java.util.ArrayList;

public class DataProducts {
    private String message;
    private ArrayList<Producto> productList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Producto> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Producto> productList) {
        this.productList = productList;
    }
}
