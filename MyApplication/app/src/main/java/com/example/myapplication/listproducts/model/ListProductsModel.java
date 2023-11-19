package com.example.myapplication.listproducts.model;

import android.util.Log;

import com.example.myapplication.addproducts.ContractAddProducts;
import com.example.myapplication.addproducts.data.DataProducts;
import com.example.myapplication.addproducts.presenter.AddProductPresenter;
import com.example.myapplication.beans.Producto;
import com.example.myapplication.listproducts.ContractListProducts;
import com.example.myapplication.listproducts.data.DataListProducts;
import com.example.myapplication.listproducts.presenter.ListProductsPresenter;
import com.example.myapplication.utils.ApiService;
import com.example.myapplication.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductsModel implements ContractListProducts.Model {
    private static final String IP_BASE = "192.168.1.196:8080";
    private ListProductsPresenter presenter;
    public ListProductsModel(ListProductsPresenter presenter){this.presenter = presenter;}

    @Override
    public void listProductsApi(Producto producto, ContractListProducts.Model.OnListProductsListener onListProductsListener) {
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "/untitled/").create(ApiService.class);
//        Log.d("PRODUCTO LISTO PARA AÑADIR:", productsList.get(0).toString());

        Call<DataListProducts> call = apiService.getDataListProducts("PRODUCT.FILTER", producto.getNombre());
        call.enqueue(new Callback<DataListProducts>() {
            @Override
            public void onResponse(Call<DataListProducts> call, Response<DataListProducts> response) {
                if (response.isSuccessful()){
                    DataListProducts dataListProducts = response.body();
                    ArrayList<Producto> productsList = dataListProducts.getProductsList();
                    for (Producto producto : productsList) {
                        Log.i("prod", producto.toString());

                    }
                    onListProductsListener.onFinished(productsList);
                }else{
                    Log.e("Response Error", "HTTP state:38:" + response.code());
                    try{
                        String errorBody = response.errorBody().string();
                        Log.e("Response error", "Error body:41: " + errorBody);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataListProducts> call, Throwable t) {
                Log.e("Response Error", "Error body:50:" + t.getMessage());
            }
        });

    }
}
