package com.example.myapplication.buyproduct;

import com.example.myapplication.beans.Producto;
import com.example.myapplication.listcategoriedproducts.ContractListCategoriedProducts;

import java.util.ArrayList;

public interface ContractBuyProduct {
    public interface Presenter{
        void buyProduct(Producto producto);
    }

    public interface Model{
        void buyProductApi(Producto producto,
                                       ContractBuyProduct.Model.OnBuyProductListener onBuyProductListener);

        interface OnBuyProductListener {
            void onFinished(ArrayList<Producto> productsList);
            void onFailure(String err);
        }

    }
    public interface View{
        public void successBuyProduct(ArrayList<Producto> productsList);
        void failureBuyProduct(String err);
    }
}
