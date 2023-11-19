package com.example.myapplication.addproducts;

import com.example.myapplication.beans.Producto;

import java.util.ArrayList;

public interface ContractAddProducts {

    public interface Presenter{
        void addProduct(Producto producto);
    }

    public interface Model{
        void addProductsApi(Producto producto,
                       OnAddProductListener onAddProductListener);

        interface OnAddProductListener{
            void onFinished(Producto producto);
            void onFailure(String err);
        }

    }
    public interface View{
        public void successAddProduct(Producto producto);
        void failureMovies(String err);
    }

}
