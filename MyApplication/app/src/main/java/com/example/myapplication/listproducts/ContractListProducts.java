package com.example.myapplication.listproducts;

import com.example.myapplication.beans.Producto;

import java.util.ArrayList;

public interface ContractListProducts {

    public interface Presenter{
        void listProducts(Producto producto);
    }

    public interface Model{
        void listProductsApi(Producto producto,
                             ContractListProducts.Model.OnListProductsListener onListProductsListener);

        interface OnListProductsListener{
            void onFinished(ArrayList<Producto> productsList);
            void onFailure(String err);
        }

    }
    public interface View{
        public void successListProducts(ArrayList<Producto> productsList);
        void failureMovies(String err);
    }
    
}
