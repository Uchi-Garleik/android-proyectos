package com.example.myapplication.listproducts;

import com.example.myapplication.beans.Producto;

import java.util.ArrayList;

public interface ContractListMyProducts {

    public interface Presenter{
        void listMyProducts(Producto producto);
    }

    public interface Model{
        void listMyProductsApi(Producto producto,
                               OnListMyProductsListener onListMyProductsListener);

        interface OnListMyProductsListener {
            void onFinished(ArrayList<Producto> productsList);
            void onFailure(String err);
        }

    }
    public interface View{
        public void successListMyProducts(ArrayList<Producto> productsList);
        void failureMovies(String err);
    }
    
}
