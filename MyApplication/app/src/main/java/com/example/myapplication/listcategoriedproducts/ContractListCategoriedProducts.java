package com.example.myapplication.listcategoriedproducts;

import com.example.myapplication.beans.Producto;

import java.util.ArrayList;

public interface ContractListCategoriedProducts {
    public interface Presenter{
        void listCategoriedProducts(Producto producto);
    }

    public interface Model{
        void listCategoriedProductsApi(Producto producto,
                               ContractListCategoriedProducts.Model.OnListCategoriedProductsListener onListCategoriedProductsListener);

        interface OnListCategoriedProductsListener {
            void onFinished(ArrayList<Producto> productsList);
            void onFailure(String err);
        }

    }
    public interface View{
        public void successListCategoriedProducts(ArrayList<Producto> productsList);
        void failureListCategoriedProducts(String err);
    }
}
